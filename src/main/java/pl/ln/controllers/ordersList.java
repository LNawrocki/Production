package pl.ln.controllers;

import pl.ln.classes.DbUtil;
import pl.ln.entity.Order;
import pl.ln.entity.OrderDao;
import pl.ln.methods.CreateOrderFromArray;
import pl.ln.methods.ReadXlsToArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/main")
public class ordersList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());


        List<String> fileList = new ArrayList<>();
//        String folderPath = "\\\\10.1.10.100\\it\\orders\\";
        String folderPath = "C:\\Kurs\\Production\\xls_import\\";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        for (File file : listOfFiles) {
            if (file.getName().contains(".xls")) {
                fileList.add(file.getName().replace(folderPath, ""));
            }
        }

        List<Order> orderList = new ArrayList<>();

        for (String file : fileList) {
            String pathToFile = folderPath + file;
//            String pathToFile = folderPath + "FR-B-0204-Z1-TA-OUDIN DENTAIRE.xls";
            System.out.println(file);
            String[][] xlsContent = ReadXlsToArray.readXlsToArray(pathToFile);
            Order order = CreateOrderFromArray.createOrderFromArray(xlsContent);

            orderList.add(order);
//        }

//        System.out.println(orderList);

            OrderDao orderDao = new OrderDao();
//            orderDao.create(order);
          Order[] ordersList = orderDao.printAllOrders();
          req.setAttribute("ordersList", ordersList);
          getServletContext().getRequestDispatcher("/WEB-INF/views/mainPage.jsp").forward(req, resp);
    }}
}
