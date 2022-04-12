package controller;

import dao.GoodDao;
import dao.SalesDao;

import model.Good;
import model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Artyom Kulagin
 */
public class CRUDServletTest extends Mockito {

    @Mock
    private GoodDao goodDao;
    @Mock
    private SalesDao salesDao;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private CRUDServlet servlet;
    @Mock
    RequestDispatcher dispatcher;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    private final static Good GOOD = new Good(1, "name", 100);
    private final static Sale SALE = new Sale(1, 1, 1);

    @Test
    public void shouldCallUpdateMethodWithCurrentGood() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("price")).thenReturn("100");
        when(request.getRequestURI()).thenReturn("/updategood");

        servlet.doPost(request, response);
        verify(goodDao).update(GOOD);
    }

    @Test
    public void shouldCallFindMethodAndForward() throws Exception {
        when(request.getRequestURI()).thenReturn("/editgood");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doPost(request,response);
        verify(goodDao).find(1);
        verify(dispatcher).forward(request,response);
    }

    @Test
    public void shouldCallDeleteMethod() throws Exception{
        when(request.getRequestURI()).thenReturn("/deletegood");
        when(request.getParameter("id")).thenReturn("1");

        servlet.doPost(request, response);
        verify(goodDao).delete(any(Good.class));
    }

    @Test
    public void shouldCallSaveMethodWithCurrentGood() throws Exception {
        when(request.getRequestURI()).thenReturn("/insertgood");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("price")).thenReturn("100");

        servlet.doPost(request, response);
        verify(goodDao).save(any(Good.class));
    }

    @Test
    public void shouldCallForwardToCreateGood() throws Exception{
        when(request.getRequestURI()).thenReturn("/newgood");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(request,response);
        verify(dispatcher).forward(request,response);
    }

    @Test
    public void shouldCallUpdateMethodWithCurrentSale() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("count")).thenReturn("1");
        when(request.getParameter("goodId")).thenReturn("1");
        when(request.getRequestURI()).thenReturn("/updatesale");

        servlet.doPost(request, response);
        verify(salesDao).update(SALE);
    }

    @Test
    public void shouldCallFindSaleMethodAndForward() throws Exception {
        when(request.getRequestURI()).thenReturn("/editsale");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doPost(request,response);
        verify(salesDao).find(1);
        verify(dispatcher).forward(request,response);
    }

    @Test
    public void shouldCallDeleteSaleMethod() throws Exception{
        when(request.getRequestURI()).thenReturn("/deletesale");
        when(request.getParameter("id")).thenReturn("1");

        servlet.doPost(request, response);
        verify(salesDao).delete(any(Sale.class));
    }

    @Test
    public void shouldCallSaveMethodWithCurrentSale() throws Exception {
        when(request.getRequestURI()).thenReturn("/insertsale");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("count")).thenReturn("1");
        when(request.getParameter("goodId")).thenReturn("1");

        servlet.doPost(request, response);
        verify(salesDao).save(any(Sale.class));
    }

    @Test
    public void shouldCallForwardToCreateSale() throws Exception{
        when(request.getRequestURI()).thenReturn("/newsale");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(request,response);
        verify(dispatcher).forward(request,response);
    }
}
