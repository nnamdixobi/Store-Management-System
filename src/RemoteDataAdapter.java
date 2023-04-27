import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    @Override
    public void connect() {
        try {
            s = new Socket("localhost", 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveProduct(ProductModel product) {
        RequestModel req = new RequestModel();
        req.code = req.SAVE_PRODUCT_REQUEST;
        req.body = gson.toJson(product);

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            }
            else{
                System.out.println("Product Saved");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductModel loadProduct(int productID) {
        RequestModel req = new RequestModel();
        req.code = req.LOAD_PRODUCT_REQUEST;
        req.body = String.valueOf(productID);

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else         // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a product with that ID!");
                    return null;
                }
                else {
                    ProductModel model = gson.fromJson(res.body, ProductModel.class);
                    System.out.println("Receiving a ProductModel object");
                    System.out.println("ProductID = " + model.productID);
                    System.out.println("Product name = " + model.name);
                    return model; // found this product and return!!!
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public UserModel loadUser(UserModel user) {
        RequestModel req = new RequestModel();
        req.code = req.USER_REQUEST;
        req.body = gson.toJson(user);

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else         // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find this user!");
                    return null;
                }
                else {
                    UserModel currentUser = gson.fromJson(res.body, UserModel.class);
                    System.out.println("Receiving a UserModel object");
                    System.out.println("UserID = " + currentUser.userID);
                    System.out.println("Username = " + currentUser.userName);
                    return currentUser; // found this user and return!!!
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Boolean saveOrder(OrderModel order) {
        RequestModel req = new RequestModel();
        req.code = req.SAVE_ORDER_REQUEST;
        req.body = gson.toJson(order);

        String json = gson.toJson(req);
        Boolean returnStatement = false;
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            }
            else{
                System.out.println("Order Saved");
                //returnStatement = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnStatement;
    }

    @Override
    public Integer loadOrders() {
        RequestModel req = new RequestModel();
        req.code = req.ORDER_REQUEST;

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            }
            else         // this is a JSON string for a product information
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find this user!");
                    return null;
                }
                else {
                    int totalRows = Integer.parseInt(res.body);
                    return totalRows; // found this user and return!!!
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}