package pl.ln;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findFile")
public class FindFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/views/findFile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String findWord = req.getParameter("findWord");

        List<String> fileList = new ArrayList<>();

        File folder = new File("\\\\10.1.10.100\\orders_2023");
        File[] listOfFiles = folder.listFiles();
        for (File listOfFile : listOfFiles) {
            if (listOfFile.getName().contains(".xls") && listOfFile.getName().contains(findWord)) {
                fileList.add(listOfFile.getName());
                resp.getWriter().println(listOfFile.getName());
            }
        }
        req.setAttribute("fileList", fileList);

        getServletContext().getRequestDispatcher("/WEB-INF/views/chooseFile.jsp").forward(req, resp);


    }
}
