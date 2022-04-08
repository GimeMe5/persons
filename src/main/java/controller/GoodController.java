package controller;

import dao.GoodDao;
import model.Good;

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
public class GoodController extends HttpServlet {
    private GoodDao goodDao = GoodDao.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/insert":
                    insertGood(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateGood(req, resp);
                    break;
                default:
                    listGood(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertGood(req, resp);
                    break;
                case "/delete":
                    deleteGood(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateGood(req, resp);
                    break;
                default:
                    listGood(req, resp);
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

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Optional<Good> exitingGood = goodDao.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/editform.jsp");
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

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/addgoodform.jsp");
        dispatcher.forward(req,resp);
    }

    private void listGood(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/jsp/viewgoods.jsp");

        List<Good> goods = goodDao.findAll();
        req.setAttribute("list", goods);

        dispatcher.forward(req,resp);
    }
}
