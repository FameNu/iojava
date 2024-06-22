package db;

import java.sql.*;

public class DatabaseClass {
    private final static String connectionUrl = "jdbc:db2://10.4.53.38:50000/SAMPLE";
    private final static String driverName = "com.ibm.db2.jcc.DB2Driver";
    private Connection connection;
    private final static String username = "st10565066";
    private final static String password = "db2103";

    public DatabaseClass() {
        try {
            Class.forName(driverName).newInstance();
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void testConnection() {
        try {
            Statement sqlStatement = this.connection.createStatement();
            String sql = "SELECT * FROM PRODUCTS WHERE PRICE > 15";
            ResultSet resultSet = sqlStatement.executeQuery(sql);

            while (resultSet.next()) {
                // print name, type, price format
                System.out.println("Name: " + resultSet.getString("NAME") + "Type: " + resultSet.getString("TYPE") + "Price: " + resultSet.getString("PRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet findCustomer(String name) {
        try {
            Statement sqlStatement = this.connection.createStatement();
            String sql = "select c.firstname, c.lastname, o.orderId, o.orderDate, p.name, od.quantity, od.quantity * od.price as total, od.price " +
                    "from customers c " +
                    "join orders o on c.customerId = o.customerId " +
                    "join orderDetails od on o.orderId = od.orderId " +
                    "join products p on od.productId = p.id " +
                    "where c.firstname = '" + name + "'";
            ResultSet resultSet = sqlStatement.executeQuery(sql);
            System.out.println(resultSet.getMetaData().getColumnCount());
            System.out.println(resultSet.getMetaData().getColumnName(1));
//            while (resultSet.next()) {
//                System.out.println("First Name: " + resultSet.getString("FIRSTNAME") + "Last Name: " + resultSet.getString("LASTNAME") + "Order ID: " + resultSet.getString("ORDERID") + "Order Date: " + resultSet.getString("ORDERDATE") + "Product Name: " + resultSet.getString("NAME") + "Quantity: " + resultSet.getString("QUANTITY") + "Total: " + resultSet.getString("TOTAL") + "Price: " + resultSet.getString("PRICE"));
//            }
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
