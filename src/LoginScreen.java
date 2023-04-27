import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private JTextField txtUserName = new JTextField(30);
    private JPasswordField txtPassword = new JPasswordField(30);
    private JButton btnLogin = new JButton("Login");

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public LoginScreen() {

        // Set the window size and position
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        // Set the layout and background color of the content pane
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(61, 90, 128));

        // Create a panel for the title and add it to the top of the content pane
        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(new Color(23, 35, 51));
        JLabel title = new JLabel("Nnamdi's Store Management System");
        title.setForeground(new Color(240, 240, 240));
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitle.add(title);
        this.getContentPane().add(panelTitle, BorderLayout.NORTH);

        // Create a panel for the input fields and add it to the center of the content pane
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridBagLayout());
        panelInput.setBackground(new Color(61, 90, 128));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelUserName = new JLabel("Username:");
        labelUserName.setForeground(new Color(240, 240, 240));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInput.add(labelUserName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelInput.add(txtUserName, gbc);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setForeground(new Color(240, 240, 240));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInput.add(labelPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelInput.add(txtPassword, gbc);

        this.getContentPane().add(panelInput, BorderLayout.CENTER);

        // Create a panel for the login button and add it to the bottom of the content pane
        JPanel panelButton = new JPanel();
        panelButton.setBackground(new Color(23, 35, 51));
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setForeground(new Color(51, 51, 51)); // set button text color to dark gray
        panelButton.add(btnLogin);
        this.getContentPane().add(panelButton, BorderLayout.SOUTH);

        // Set the window to be visible
        this.setVisible(true);
    }
}


