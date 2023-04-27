public class StoreManager {

    private static StoreManager instance = null;

    private RemoteDataAdapter dao;

    private ProductView productView = null;
    private LoginScreen loginScreen = null;
    private CheckoutScreen checkoutScreen = null;

    public ProductView getProductView() { return productView; }
    public LoginScreen getLoginScreen() { return loginScreen; }
    public CheckoutScreen getCheckoutScreen() { return checkoutScreen; }

    private ProductController productController = null;
    private LoginController loginController = null;
    private CheckoutController checkoutController = null;

    public static StoreManager getInstance() {
        if (instance == null)
            instance = new StoreManager("SQLite");
        return instance;
    }

    private UserModel currentUser;

    public void setCurrentUser(UserModel user) {
        currentUser = user;
        this.checkoutController.setCurrentUser(this.currentUser);
    }

    public RemoteDataAdapter getDataAccess() {
        return dao;
    }

    private StoreManager(String db) {
        // do some initialization here!!!
        dao = new RemoteDataAdapter();
        dao.connect();

        //Views
        productView = new ProductView();
        loginScreen = new LoginScreen();
        checkoutScreen = new CheckoutScreen();

        //Controller Objects
        productController = new ProductController(productView, dao);
        loginController = new LoginController(loginScreen, dao);
        checkoutController = new CheckoutController(checkoutScreen, dao);
    }

}
