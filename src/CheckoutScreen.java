import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutScreen extends JFrame {

    private JButton btnAdd = new JButton("Add a new item");
    private JButton btnPay = new JButton("Finish and Pay");
    private JButton btnLogout = new JButton("Log out");

    private DefaultTableModel items = new DefaultTableModel();
    private JTable tblItems = new JTable(items);
    private JLabel labTotal = new JLabel("Total: ");

    public CheckoutScreen() {

        this.setTitle("Checkout");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(400, 600);

        items.addColumn("Product ID");
        items.addColumn("Name");
        items.addColumn("Price");
        items.addColumn("Quantity");
        items.addColumn("Cost");

        JPanel panelOrder = new JPanel();
        panelOrder.setPreferredSize(new Dimension(400, 450));
        panelOrder.setLayout(new BoxLayout(panelOrder, BoxLayout.PAGE_AXIS));
        tblItems.setBounds(0, 0, 400, 350);
        panelOrder.add(tblItems.getTableHeader());
        panelOrder.add(tblItems);
        panelOrder.add(labTotal);
        tblItems.setFillsViewportHeight(true);
        panelOrder.setBackground(new java.awt.Color(153, 204, 255));
        this.getContentPane().add(panelOrder);

        JPanel panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(400, 100));
        panelButton.setBackground(new java.awt.Color(153, 204, 255));
        btnAdd.setPreferredSize(new Dimension(150, 50));
        btnPay.setPreferredSize(new Dimension(150, 50));
        btnLogout.setPreferredSize(new Dimension(150, 50));
        panelButton.add(btnAdd);
        panelButton.add(btnPay);
        panelButton.add(btnLogout);
        this.getContentPane().add(panelButton);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement log out functionality here
                dispose(); // close the current frame
                new LoginScreen(); // open a new login screen
            }
        });
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnPay() {
        return btnPay;
    }

    public JLabel getLabTotal() {
        return labTotal;
    }

    public void addRow(Object[] row) {
        items.addRow(row);
    }
}
