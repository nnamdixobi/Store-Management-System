import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    LoginScreen loginScreen;
    DataAccess myDao;

    public LoginController(LoginScreen screen, DataAccess dao) {
        loginScreen = screen;
        myDao = dao;
        loginScreen.getBtnLogin().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginScreen.getBtnLogin()) {
            String username = loginScreen.getTxtUserName().getText().trim();
            String password = loginScreen.getTxtPassword().getText().trim();

            System.out.println("Login with username = " + username + " and password = " + password);
            UserModel user = new UserModel();
            user.userName = username;
            user.password = password;
            user = myDao.loadUser(user);

            if (user == null) {
                JOptionPane.showMessageDialog(null, "This user does not exist!");
            } else if (user.isSeller == true) { //opens seller screen
                this.loginScreen.setVisible(false);
                StoreManager.getInstance().getProductView().setVisible(true);
            } else { //opens buyer screen
                StoreManager.getInstance().setCurrentUser(user);
                this.loginScreen.setVisible(false);
                StoreManager.getInstance().getCheckoutScreen().setVisible(true);
            }
        }
    }
}
