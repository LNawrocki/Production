package pl.ln.controllers;

import pl.ln.entity.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/clear")
public class OrderClear extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrderDao orderDao = new OrderDao();
        orderDao.delete(); // czyszczenie tablicy orders
        resp.sendRedirect("/order/list");
    }
}
