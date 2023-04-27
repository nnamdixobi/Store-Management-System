import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CheckoutController implements ActionListener {
    CheckoutScreen checkoutScreen;
    DataAccess myDAO;
    OrderModel orderModel = new OrderModel();
    UserModel currentUser = new UserModel();

    public CheckoutController(CheckoutScreen screen, DataAccess dao) {
        checkoutScreen = screen;
        myDAO = dao;

        checkoutScreen.getBtnAdd().addActionListener(this);
        checkoutScreen.getBtnPay().addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkoutScreen.getBtnAdd()) {
            addProduct();
        }

        if (e.getSource() == checkoutScreen.getBtnPay()) {
            saveOrder();
        }
    }

    private void saveOrder() {

        Date currentDate = new Date(System.currentTimeMillis());

        try {
            if (orderModel.totalCost == 0) { //when there's no items in the checkout
                JOptionPane.showMessageDialog(null, "There are no items in your checkout!");
            } else {
                int totalOrderRows = myDAO.loadOrders(); //gets total number of rows in Order table to make id later
                if (totalOrderRows >= 0) { //if negative, then there was an error accessing database
                        int orderID = totalOrderRows + 1;
                        orderModel.orderID = orderID;
                        orderModel.totalCost = orderModel.totalCost;
                        orderModel.date = currentDate;
                        orderModel.customerName = currentUser.userName;
                        Boolean orderSaved = myDAO.saveOrder(orderModel);

                        if (orderSaved) { //if accessing the database and uploading order was successful
                             JOptionPane.showMessageDialog(null, "Your order has been made!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Your order has been made");
                        }
                } else {
                    JOptionPane.showMessageDialog(null, "There was a problem making your order!");
                }
            }
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for numbers!");
            ex.printStackTrace();
        }
    }

    private void addProduct() {
        String id = JOptionPane.showInputDialog("Enter ProductID: ");
        ProductModel product = myDAO.loadProduct(Integer.parseInt(id));
        if (product == null) {
            JOptionPane.showMessageDialog(null, "This product does not exist!");
            return;
        }

        double quantity = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter quantity: "));

        if (quantity < 0 || quantity > product.quantity) {
            JOptionPane.showMessageDialog(null, "This quantity is not valid!");
            return;
        }

        OrderLineModel orderLineModel = new OrderLineModel();
        orderLineModel.orderID = orderModel.orderID;
        orderLineModel.productID = product.productID;
        orderLineModel.quantity = quantity;
        orderLineModel.cost = quantity * product.price;

        product.quantity = product.quantity - quantity; // update new quantity!!
        myDAO.saveProduct(product); // and store this product back right away!!!

        orderModel.lines.add(orderLineModel);
        orderModel.totalCost =  orderModel.totalCost + orderLineModel.cost;

        Object[] row = new Object[5];
        row[0] = orderLineModel.productID;
        row[1] = product.name;
        row[2] = product.price;
        row[3] = orderLineModel.quantity;
        row[4] = orderLineModel.cost;

        checkoutScreen.addRow(row);
        checkoutScreen.getLabTotal().setText("Total: $" + orderModel.totalCost);
        checkoutScreen.invalidate();
    }

    // this method is used for instantiating the currentUser variable, so it can be used when creating
    // a new order to add back to the datatable
    public void setCurrentUser(UserModel user) {
        currentUser = user;
    }

}