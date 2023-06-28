package pl.ln;

import pl.ln.classes.Order;
import pl.ln.methods.CreateOrderFromArray;
import pl.ln.methods.CreatePosFromArray;
import pl.ln.methods.ReadXlsToArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> fileList = new ArrayList<>();

//        File folder = new File("C:\\Kurs\\Production\\xls_import");
        File folder = new File("\\\\10.1.10.100\\it\\orders\\");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.getName().contains(".xls")) {
                fileList.add(file.getName().replace("\\\\10.1.10.100\\it\\orders\\", ""));
            }
        }

        List<Order> orderList = new ArrayList<>();

        for (String file : fileList) {
            String pathToFile = "\\\\10.1.10.100\\it\\orders\\" + file;
            System.out.println(file);
            String[][] xlsContent = ReadXlsToArray.readXlsToArray(pathToFile);
            Order order = CreateOrderFromArray.createOrderFromArray(xlsContent);
            orderList.add(order);
        }
        System.out.println(orderList);
    }
}