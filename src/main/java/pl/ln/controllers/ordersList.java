package pl.ln.controllers;

import pl.ln.classes.DbUtil;
import pl.ln.entity.Order;
import pl.ln.entity.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/main")
public class ordersList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM orders");
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        OrderDao orderDao = new OrderDao();
        Order[] ordersList = orderDao.printAllOrders();

        req.setAttribute("orderList", ordersList);
        getServletContext().getRequestDispatcher("/mainPage.jsp").forward(req, resp);
    }
}
