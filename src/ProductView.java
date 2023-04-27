import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView extends JFrame {
    public JTextField txtProductID = new JTextField(20);
    public JTextField txtProductName = new JTextField(20);
    public JTextField txtProductPrice = new JTextField(20);
    public JTextField txtProductQuantity = new JTextField(20);

    public JButton btnLoad = new JButton("Load");
    public JButton btnSave = new JButton("Save");
    public JButton btnLogout = new JButton("Logout");

    public ProductView() {

        this.setTitle("Product View");
        this.setSize(new Dimension(425, 450));
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));    // make this window with box layout

        JPanel line1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel idLabel = new JLabel("Product ID:");
        idLabel.setPreferredSize(new Dimension(100, 25));
        line1.add(idLabel);
        line1.add(txtProductID);
        line1.setBackground(new java.awt.Color(119, 181, 254));
        this.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setPreferredSize(new Dimension(100, 25));
        line2.add(nameLabel);
        line2.add(txtProductName);
        line2.setBackground(new java.awt.Color(119, 181, 254));
        this.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setPreferredSize(new Dimension(100, 25));
        line3.add(priceLabel);
        line3.add(txtProductPrice);
        line3.setBackground(new java.awt.Color(119, 181, 254));
        this.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setPreferredSize(new Dimension(100, 25));
        line4.add(quantityLabel);
        line4.add(txtProductQuantity);
        line4.setBackground(new java.awt.Color(119, 181, 254));
        this.getContentPane().add(line4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new java.awt.Color(119, 181, 254));
        btnLoad.setPreferredSize(new Dimension(100, 30));
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnLogout.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLogout);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform logout action here
                dispose();  // close the ProductView window
                LoginScreen loginScreen = new LoginScreen(); // open the LoginScreen window
                loginScreen.setVisible(true); // make the LoginScreen window visible
            }
        });

        this.getContentPane().add(buttonPanel);
    }

}
