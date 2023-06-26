package pl.ln;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
@WebServlet("/showFile")
public class ShowFile extends HttpServlet {

    String orderNumber = "";
    String idNumber = "";
    String client = "";
    String agent = "";
    String delDate = "";
    String quality = "";
    String country = "";
    String delType = "";
    String finalDest = "";
    String additionalInfo = "brak";

    String posNr = "";
    String articleCode = "";
    String pcs = "";
    String unit = "";
    String additionalInformation = "";


    int headerNextLastRowIndex = 0;
    boolean additionalInfoSet = false;
    int firstPosRowNumber;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String fileName = req.getParameter("fileName");

        String[][] xlsContent = new String[0][];

//        File myFile = new File("\\\\10.1.10.100\\orders_2023\\" + fileName);
        File myFile = new File("C:\\Kurs\\Production\\UK-C-0028-Z10.xls");
        FileInputStream fis = new FileInputStream(myFile);
        // Finds the workbook instance for XLSX file
        HSSFWorkbook myWorkBook = new HSSFWorkbook(fis);
        // Return first sheet from the XLSX workbook
        HSSFSheet mySheet = myWorkBook.getSheetAt(0);
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext() ) {
            Row row = rowIterator.next();
            // For each row, iterate through each columns
            xlsContent = Arrays.copyOf(xlsContent, xlsContent.length + 1);

            Iterator<Cell> cellIterator = row.cellIterator();
            String[] xlsRow = new String[0];
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellContent = cell.getStringCellValue();
                xlsRow = Arrays.copyOf(xlsRow, xlsRow.length + 1);
                xlsRow[xlsRow.length-1] = cellContent.replace("\n", " ");
            }

            xlsContent[xlsContent.length-1] = xlsRow;
        }


        for (int i = 0; i < xlsContent.length; i++) {
            for (int j = 0; j < xlsContent[i].length; j++) {

                if (xlsContent[i][j].contains("ORDER")) {
                    orderNumber = xlsContent[i][j].split("\\s/\\s")[xlsContent[i][j].split("\\s/\\s").length-1];
                }
                if (xlsContent[i][j].contains("ID:")) {
                    idNumber = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("Client:")) {
                    client = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("Agent:")) {
                    agent = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("delDate:")) {
                    delDate = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("Quantity:")) {
                    quality = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("Country:")) {
                    country = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("Delivery:")) {
                    delType = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                }
                if (xlsContent[i][j].contains("Final Dest.:")) {
                    finalDest = xlsContent[i][j].split(":")[xlsContent[i][j].split(":").length-1];
                    headerNextLastRowIndex = i+1;
                }

//                resp.getWriter().print(xlsContent[i][j] + " * ");
            }

            if (!additionalInfoSet && headerNextLastRowIndex != 0 && i > headerNextLastRowIndex && xlsContent[i].length > 0 && !xlsContent[i][0].equals("Pos")) {
                additionalInfo = xlsContent[i][0];
                additionalInfoSet = true;

            }

            if (xlsContent[i].length > 0) {
                if (xlsContent[i][0].contains("Pos.") && !xlsContent[i][0].equals("")) {
                    firstPosRowNumber = i + 1;
                }
            }
//            for (int k = firstPosRowNumber; k < xlsContent.length; k++ ) {
//                for (int j = 0; j < xlsContent[k].length; j++){
//                    resp.getWriter().println(xlsContent[k][j]);
//                }
//            }



//            resp.getWriter().println("<br>");
        }
        resp.getWriter().println("orderNumber: " + orderNumber + "<br>" +
                "idNumber: " + idNumber + "<br>" +
                "client: " + client + "<br>" +
                "agent: " + agent + "<br>" +
                "delDate: " + delDate + "<br>" +
                "quality: " + quality + "<br>" +
                "country: " + country + "<br>" +
                "delType: " + delType + "<br>" +
                "finalDest: " + finalDest + "<br>" +
                "additionalInfo: " + additionalInfo + "<br>");

    }
}
