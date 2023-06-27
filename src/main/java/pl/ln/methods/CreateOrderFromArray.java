package pl.ln.methods;

import pl.ln.classes.Order;

public class CreateOrderFromArray {
    public static Order createOrderFromArray(String[][] array) {

        int headerNextLastRowIndex = 0;
        boolean additionalInfoSet = false;



        Order order = new Order();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j].contains("ORDER")) {
                    order.setOrderNumber(array[i][j].split("\\s/\\s")[array[i][j].split("\\s/\\s").length - 1]);
                }
                if (array[i][j].contains("ID:")) {
                    order.setOrderID(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("Client:")) {
                    order.setClient(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("Agent:")) {
                    order.setAgent(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("delDate:")) {
                    order.setDelDate(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("Quantity:")) {
                    order.setQuality(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("Country:")) {
                    order.setCountry(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("Delivery:")) {
                    order.setDelType(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                }
                if (array[i][j].contains("Final Dest.:")) {
                    order.setFinalDest(array[i][j].split(":")[array[i][j].split(":").length - 1]);
                    headerNextLastRowIndex = i+1;
                }
            }
            if (!additionalInfoSet && headerNextLastRowIndex != 0 && i > headerNextLastRowIndex && array[i].length > 0 && !array[i][0].equals("Pos")) {
                order.setAdditionalInfo(array[i][0]);
                additionalInfoSet = true;
            }
        }
        return order;
    }
}
