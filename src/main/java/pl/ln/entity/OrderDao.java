package pl.ln.entity;
import pl.ln.classes.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class OrderDao {

    private  static final String CREATE_ORDER_QUERY = "INSERT INTO orders (" +
            "order_id, order_number, agent, delivery_date, quality, country, delivery_type, final_dest, additional_info, pos_table_name, order_date, order_no) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private  static final String CREATE_POS_IN_ORDER_QUERY = "INSERT INTO pos_table_name (pos, article_code, pcs, unit, additional_information) VALUES (?, ?, ?, ?, ?)";
//    private  static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, userName = ?, password = ? WHERE id = ?";
//    public static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    public static final String SELECT_ORDERS_QUERY = "SELECT * FROM orders";
    public static final String SELECT_POS_IN_ORDER_QUERY = "SELECT pos, article_code, pcs, unit, additional_information FROM pos_table_name";
//    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";


    public Order[] printAllOrders() {

        Order[] tempOrder = new  Order[0];
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SELECT_ORDERS_QUERY);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getString("order_id"));
                order.setOrderNumber(resultSet.getString("order_number"));
                order.setAgent(resultSet.getString("agent"));
                order.setDeliveryDate(resultSet.getString("delivery_date"));
                order.setQuality(resultSet.getString("quality"));
                order.setCountry(resultSet.getString("country"));
                order.setDeliveryType(resultSet.getString("delivery_type"));
                order.setFinalDest(resultSet.getString("final_dest"));
                order.setAdditionalInfo(resultSet.getString("additional_info"));
                order.setPos(printAllPos());
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
    public Order create(Order order) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getOrderId());
            statement.setString(2, order.getOrderNumber());
            statement.setString(3, order.getAgent());
            statement.setString(4, order.getDeliveryDate());
            statement.setString(5, order.getQuality());
            statement.setString(6, order.getCountry());
            statement.setString(7, order.getDeliveryType());
            statement.setString(8, order.getFinalDest());
            statement.setString(9, order.getAdditionalInfo());
            statement.setString(10, order.getOrderId());
            statement.setString(11, order.getOrderDate());
            statement.setString(12, order.getOrderNo());

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
//
//
//    public String hashPassword(String password) {
//
//        return BCrypt.hashpw(password, BCrypt.gensalt());
//    }


}
