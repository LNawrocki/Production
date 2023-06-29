package pl.ln;

import pl.ln.classes.Order;
import pl.ln.methods.CreateOrderFromArray;
import pl.ln.methods.CreatePosFromArray;
import pl.ln.methods.ReadXlsToArray;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

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
//            String pathToFile = folderPath + "L-4009-Z1-PW-LUFTHANSA.xls";
            System.out.println(file);
            String[][] xlsContent = ReadXlsToArray.readXlsToArray(pathToFile);
            Order order = CreateOrderFromArray.createOrderFromArray(xlsContent);
            orderList.add(order);
//        }
        }
        System.out.println(orderList);
    }
}