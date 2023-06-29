package pl.ln.methods;

import pl.ln.classes.Order;

import java.util.regex.Pattern;

public class CreateOrderFromArray {
    private static final Pattern IS_last_ROW = Pattern.compile("[0-9]+\\.[0-9]{2}\\.[0-9]{4}");
    public static Order createOrderFromArray(String[][] array) {

        int headerNextLastRowIndex = 0;
        boolean additionalInfoSet = false;
        boolean additionalInfoExist = true;

        Order order = new Order();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j].contains("ORDER")) {
                    if (array[i][j].split("\\s/\\s").length == 1) {
                        order.setOrderNumber("");
                    } else {
                        order.setOrderNumber(array[i][j].split("\\s/\\s")[array[i][j].split("\\s/\\s").length - 1]);
                    }
                }

                if (array[i][j].contains("ID:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setOrderId("");
                    } else {
                        order.setOrderId(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }

                if (array[i][j].contains("Client:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setClient("");
                    } else {
                        order.setClient(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }

                if (array[i][j].contains("Agent:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setAgent("");
                    } else {
                        order.setAgent(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }

                if (array[i][j].contains("Del.-Date:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setDelDate("");
                    } else {
                        order.setDelDate(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }

                if (array[i][j].contains("Quality:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setQuality("");
                    } else {
                        order.setQuality(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }

                if (array[i][j].contains("Country:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setCountry("");
                    } else {
                        order.setCountry(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }


                if (array[i][j].contains("Delivery:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setDelType("");
                    } else {
                        order.setDelType(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                    }
                }

                if (array[i][j].contains("Final Dest.:")) {
                    if (array[i][j].split(": ").length == 1) {
                        order.setFinalDest("");
                    } else {
                        order.setFinalDest(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
                        headerNextLastRowIndex = i + 1;
                    }
                }

                if (!additionalInfoSet && headerNextLastRowIndex != 0 && i > headerNextLastRowIndex && array[i].length > 0 && array[i][0].equals("Pos.")) {
                    additionalInfoSet = false;
                    additionalInfoExist = false;
                }

                if (additionalInfoExist && !additionalInfoSet && headerNextLastRowIndex != 0 && i > headerNextLastRowIndex && array[i].length > 0 && !array[i][0].equals("Pos.")) {
                    order.setAdditionalInfo(array[i][0]);
                    additionalInfoSet = true;
                }

                if (IS_last_ROW.matcher(array[i][0]).matches()) {
                    order.setOrderDate(array[i][0]);
                    order.setOrderNo(array[i][5].replace("Order No.: ", ""));
                }
            }
        }

        order.setPos(CreatePosFromArray.createPosFromArray(array));

        return order;
    }
}

