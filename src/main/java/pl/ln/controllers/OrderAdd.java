package pl.ln.controllers;
import pl.ln.entity.Order;
import pl.ln.entity.OrderDao;
import pl.ln.methods.ReadXlsToArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/order/add")
public class OrderAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> fileList = new ArrayList<>();
        String folderPath = "C:\\Kurs\\Production\\xls_import\\";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        Arrays.sort(listOfFiles);
        for (File file : listOfFiles) {
            if (file.getName().contains(".xls")) {
                fileList.add(file.getName().replace(folderPath, ""));
            }
        }
        req.setAttribute("fileList", fileList);
        getServletContext().getRequestDispatcher("/WEB-INF/views/addOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] files = req.getParameterValues("files");
        for (String file : files) {
            System.out.println(file);
            String folderPath = "C:\\Kurs\\Production\\xls_import\\";
            String pathToFile = folderPath + file;
//            String pathToFile = folderPath + "FR-B-0204-Z1-TA-OUDIN DENTAIRE.xls";
            String[][] xlsContent = ReadXlsToArray.readXlsToArray(pathToFile);

            Order order = OrderDao.createOrderFromArray(xlsContent);
//            List<Pos> pos = OrderDao.createPosFromArray(xlsContent);
            OrderDao orderDao = new OrderDao();
            orderDao.createOrderInSql(order);
        }
        resp.sendRedirect("/order/list");
    }
}
