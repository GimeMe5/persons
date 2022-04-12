package controller;

import dao.GoodDao;
import dao.SalesDao;

import model.Good;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    private final static Good GOOD = new Good(1, "name", 100);

    @Test
    public void shouldCallUpdateMethodWithCurrentObject() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("price")).thenReturn("100");
        when(request.getRequestURI()).thenReturn("/updategood");

        servlet.doPost(request, response);
        verify(goodDao).update(any(Good.class));
    }
}
