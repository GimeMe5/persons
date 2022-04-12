package controller;

import dao.GoodDao;
import dao.SalesDao;
import model.Good;
import model.Sale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Artyom Kulagin
 */
@WebServlet("/")
public class CRUDServlet extends HttpServlet {
    private final GoodDao goodDao = new GoodDao();
    private final SalesDao salesDao = SalesDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();
        try {
            switch (action) {
                case "/insertgood":
                    insertGood(req, resp);
                    break;
                case "/editgood":
                    showEditFormGood(req, resp);
                    break;
                case "/updategood":
                    updateGood(req, resp);
                    break;
                case "/deletegood":
                    deleteGood(req, resp);
                    break;
                case "/insertsale":
                    insertSale(req, resp);
                    break;
                case "/editsale":
                    showEditFormSale(req, resp);
                    break;
                case "/updatesale":
                    updateSale(req, resp);
                    break;
                case "/deletesale":
                    deleteSale(req, resp);
                    break;
                default:
                    listGood(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getRequestURI();
        try {
            switch (action) {
                case "/newgood":
                    showNewFormGood(req, resp);
                    break;
                case "/newsale":
                    showNewFormSale(req, resp);
                    break;
                case "/listsale":
                    listSales(req,resp);
                default:
                    listGood(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateGood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));

        Good good = new Good(id, name, price);
        goodDao.update(good);
        resp.sendRedirect("list");
    }

    private void showEditFormGood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Optional<Good> exitingGood = goodDao.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/goods/editform.jsp");
        exitingGood.ifPresent(s -> req.setAttribute("good", s));
        dispatcher.forward(req, resp);
    }

    private void deleteGood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Good good = new Good(id);
        goodDao.delete(good);
        resp.sendRedirect("list");
    }

    private void insertGood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));

        Good good = new Good(name, price);
        goodDao.save(good);
        resp.sendRedirect("list");
    }

    private void showNewFormGood(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/goods/addgoodform.jsp");
        dispatcher.forward(req,resp);
    }

    private void listGood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        List<Good> goods = goodDao.findAll();
        req.setAttribute("list", goods);

        getServletContext().getRequestDispatcher("/views/jsp/goods/viewgoods.jsp").forward(req, resp);
    }

    private void updateSale(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        int goodId = Integer.parseInt(req.getParameter("goodId"));

        Sale sale = new Sale(id, count, goodId);
        salesDao.update(sale);
        resp.sendRedirect("/listsale");
    }

    private void showEditFormSale(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Optional<Sale> exitingGood = salesDao.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/sales/editform.jsp");
        exitingGood.ifPresent(s -> req.setAttribute("sale", s));
        dispatcher.forward(req, resp);
    }

    private void deleteSale(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Sale sale = new Sale(id);
        salesDao.delete(sale);
        resp.sendRedirect("/listsale");
    }

    private void insertSale(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        int goodId = Integer.parseInt(req.getParameter("goodId"));
        Sale sale = new Sale(id,count,goodId);
        salesDao.save(sale);
        resp.sendRedirect("/listsale");
    }

    private void showNewFormSale(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/sales/addsaleform.jsp");
        dispatcher.forward(req, resp);
    }

    private void listSales(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        List<Sale> sales = salesDao.findAll();
        req.setAttribute("list", sales);

        getServletContext().getRequestDispatcher("/views/jsp/sales/viewsales.jsp").forward(req, resp);
    }
}
