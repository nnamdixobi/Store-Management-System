import javax.jws.soap.SOAPBinding;

public interface DataAccess {
    void connect();

    void saveProduct(ProductModel product);
    Boolean saveOrder(OrderModel order);


    ProductModel loadProduct(int productID);

    UserModel loadUser(UserModel user);

    Integer loadOrders();
}
