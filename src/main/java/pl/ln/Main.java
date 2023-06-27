package pl.ln;

import pl.ln.classes.Order;
import pl.ln.methods.CreateOrderFromArray;
import pl.ln.methods.CreatePosFromArray;
import pl.ln.methods.ReadXlsToArray;

public class Main {
    public static void main(String[] args) {

        String pathToFile = "C:\\Kurs\\Production\\UK-C-0028-Z9.xls";
        String[][] xlsContent = ReadXlsToArray.readXlsToArray(pathToFile);
        Order order = CreateOrderFromArray.createOrderFromArray(xlsContent);
        System.out.println(order);

        CreatePosFromArray.createPosFromArray(xlsContent);



    }
}