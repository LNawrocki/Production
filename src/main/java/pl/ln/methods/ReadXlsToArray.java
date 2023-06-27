package pl.ln.methods;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Iterator;

public class ReadXlsToArray {
    public static String[][] readXlsToArray(String pathTofile) {

        String[][] xlsContent = new String[0][];

        try {
            File myFile = new File(pathTofile);
            FileInputStream fis = new FileInputStream(myFile);
            // Finds the workbook instance for XLSX file
            HSSFWorkbook myWorkBook = new HSSFWorkbook(fis);
            // Return first sheet from the XLSX workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = mySheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, iterate through each columns
                xlsContent = Arrays.copyOf(xlsContent, xlsContent.length + 1);

                Iterator<Cell> cellIterator = row.cellIterator();
                String[] xlsRow = new String[0];
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellContent = cell.getStringCellValue();
                    xlsRow = Arrays.copyOf(xlsRow, xlsRow.length + 1);
                    xlsRow[xlsRow.length - 1] = cellContent.replace("\n", " ");
                }
                xlsContent[xlsContent.length - 1] = xlsRow;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xlsContent;
    }
}
