import java.sql.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:store.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

            /* Test data!!!
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveProduct(ProductModel product) {
        try {
            Statement stmt = conn.createStatement();

            if (loadProduct(product.productID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Product(productID, name, price, quantity) VALUES ("
                        + product.productID + ","
                        + '\'' + product.name + '\'' + ","
                        + product.price + ","
                        + product.quantity + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Product SET "
                        + "productID = " + product.productID + ","
                        + "name = " + '\'' + product.name + '\'' + ","
                        + "price = " + product.price + ","
                        + "quantity = " + product.quantity +
                        " WHERE productID = " + product.productID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProductModel loadProduct(int productID) {
        ProductModel product = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product WHERE ProductID = " + productID);
            if (rs.next()) {
                product = new ProductModel();
                product.productID = rs.getInt(1);
                product.name = rs.getString(2);
                product.price = rs.getDouble(3);
                product.quantity = rs.getDouble(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public  UserModel loadUser(UserModel user) {
        String username = user.userName;
        String password = user.password;
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM User WHERE UserName = ? AND Password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new UserModel();
                user.userID = rs.getInt(1);
                user.userName = rs.getString(2);
                user.password = rs.getString(3);
                user.isSeller = rs.getBoolean(6);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public Boolean saveOrder(OrderModel order) {

        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO \"Orders\" VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, order.orderID);
            statement.setDate(2, order.date);
            statement.setString(3, order.customerName);
            statement.setDouble(4, order.totalCost);
            statement.setDouble(5, order.totalTax);

            statement.execute();    // commit to the database;
            statement.close();

            statement = conn.prepareStatement("INSERT INTO OrderLine VALUES (?, ?, ?, ?)");
            for (OrderLineModel line: order.lines) { // store for each order line!
                statement.setInt(1, order.orderID);
                statement.setInt(2, line.productID);
                statement.setDouble(3, line.quantity);
                statement.setDouble(4, line.cost);

                statement.execute();    // commit to the database;
            }
            statement.close();
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer loadOrders() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Count(OrderID) As orderRows FROM \"Orders\"");
            int rows = resultSet.getInt("orderRows");
            return rows;
        }
        catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
            return -1; //will return -1 if database error and id can't be negative
        }
    }
}
