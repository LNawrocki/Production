package pl.ln.entity;
import pl.ln.classes.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class OrderDao {

    private  static final String CREATE_ORDER_QUERY = "INSERT INTO orders (" +
            "order_id, order_number, client, agent, delivery_date, quality, country, delivery_type, final_dest, additional_info, pos_table_name, order_date, order_no) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private  static final String DELETE_ORDER_QUERY = "DELETE FROM orders";
    private  static final String CREATE_POS_IN_ORDER_QUERY = "INSERT INTO ? (pos, article_code, pcs, unit, additional_information) VALUES (?, ?, ?, ?, ?)";
//    private  static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, userName = ?, password = ? WHERE id = ?";
//    public static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_ORDERS_QUERY = "SELECT order_id, order_number, client, agent, delivery_date, quality, country, delivery_type, final_dest, additional_info, pos_table_name, order_date, order_no FROM orders";
    public static final String SELECT_POS_IN_ORDER_QUERY = "SELECT pos, article_code, pcs, unit, additional_information FROM pos_table_name";
//    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final Pattern IS_last_ROW = Pattern.compile("[0-9]+\\.[0-9]{2}\\.[0-9]{4}");
    private static final Pattern IS_POS_ROW = Pattern.compile("^[0-9]+");


    public Order[] printAllOrders() {

        Order[] tempOrder = new  Order[0];
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_ORDERS_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getString("order_id"));
                order.setOrderNumber(resultSet.getString("order_number"));
                order.setClient(resultSet.getString("client"));
                order.setAgent(resultSet.getString("agent"));
                order.setDeliveryDate(resultSet.getString("delivery_date"));
                order.setQuality(resultSet.getString("quality"));
                order.setCountry(resultSet.getString("country"));
                order.setDeliveryType(resultSet.getString("delivery_type"));
                order.setFinalDest(resultSet.getString("final_dest"));
                order.setAdditionalInfo(resultSet.getString("additional_info"));
                order.setPos(resultSet.getString("order_id"));
                order.setOrderDate(resultSet.getString("order_date"));
                order.setOrderNo(resultSet.getString("order_no"));
                tempOrder = Arrays.copyOf(tempOrder, tempOrder.length + 1);
                tempOrder[tempOrder.length-1] = order;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempOrder;
    }

    public List<Pos> printAllPos() {

       List<Pos> tempPos = new ArrayList<>();

//       try{
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_POS_IN_ORDER_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pos pos = new Pos();
                pos.setPos(resultSet.getString("pos"));
                pos.setArticleCode(resultSet.getString("article_code"));
                pos.setPcs(resultSet.getInt("pcs"));
                pos.setUnit(resultSet.getString("unit"));
                pos.setAdditionalInformation(resultSet.getString("additional_information"));
                tempPos.add(pos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tempPos;
    }



//    public void delete(int userId) {
//        try(Connection conn = DbUtil.getConnection() ) {
//            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
//            statement.setInt(1, userId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(User user) {
//        try(Connection conn = DbUtil.getConnection() ) {
//            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
//            statement.setString(1, user.getEmail());
//            statement.setString(2, user.getUserName());
//            statement.setString(3, hashPassword(user.getPassword()));
//            statement.setInt(4, user.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public User read(int userId) {
//        try(Connection conn = DbUtil.getConnection() ) {
//            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
//            statement.setInt(1, userId);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getInt("id"));
//                user.setEmail(resultSet.getString("email"));
//                user.setUserName(resultSet.getString("username"));
//                user.setPassword(resultSet.getString("password"));
//                return user;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//


    public Order createOrderInSql(Order order) {

//        try{
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getOrderId());
            statement.setString(2, order.getOrderNumber());
            statement.setString(3, order.getClient());
            statement.setString(4, order.getAgent());
            statement.setString(5, order.getDeliveryDate());
            statement.setString(6, order.getQuality());
            statement.setString(7, order.getCountry());
            statement.setString(8, order.getDeliveryType());
            statement.setString(9, order.getFinalDest());
            statement.setString(10, order.getAdditionalInfo());
            statement.setString(11, order.getOrderId());
            statement.setString(12, order.getOrderDate());
            statement.setString(13, order.getOrderNo());
            statement.executeUpdate();



            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
//                order.setId(resultSet.getInt(1));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Order createOrderFromArray(String[][] array) {

            int headerNextLastRowIndex = 0;
            boolean additionalInfoSet = false;
            boolean additionalInfoExist = true;

            Order order = new Order();

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {

                    if (array[i][j].contains("ORDER /")) {
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
                            order.setDeliveryDate("");
                        } else {
                            order.setDeliveryDate(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
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
                            order.setDeliveryType("");
                        } else {
                            order.setDeliveryType(array[i][j].split(": ")[array[i][j].split(": ").length - 1]);
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

            return order;
        }

    public static List<Pos> createPosFromArray(String[][] array) {
        int posRowNumber = 0;

        List<Pos> posList = new ArrayList<>();
        int posId = 0;
        Pos pos = null;
        boolean readPos = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i].length > 0) {
                if (array[i][0].equals("Pos.") && array[i][0].length() == 4) {
                    readPos = true;
                }
                if (array[i][0].contains("Order:")) {
                    readPos = false;
                }

                if (readPos && IS_POS_ROW.matcher(array[i][0]).find() && !IS_last_ROW.matcher(array[i][0]).matches()) {
                    posId++;
                    pos = new Pos();
                    pos.setPos(array[i][0]);

                    pos.setArticleCode(array[i][1]);
                    pos.setPcs(Integer.parseInt(array[i][2].isBlank() ? "0" : array[i][2]));
                    pos.setUnit(array[i][3]);
                    pos.setAdditionalInformation(array[i][4]);
                    posList.add(pos);

                }
            }
        }

        return posList;
    }


    public void delete() {
//        try{
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ORDER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public String hashPassword(String password) {
//
//        return BCrypt.hashpw(password, BCrypt.gensalt());
//    }


}
