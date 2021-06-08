package views.screen.cart;

import common.exception.MediaNotAvailableException;
import common.exception.PlaceOrderException;
import common.exception.ViewCartException;
import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.BaseController;
import controller.PlaceOrderController;
import controller.ViewCartController;
import entity.cart.CartItem;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseNextScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;
import views.screen.shipping.ShippingScreenHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CartScreenHandler extends BaseNextScreenHandler implements Observer {
    private static final Logger LOGGER = Utils.getLogger(CartScreenHandler.class.getName());
    final String bannerPath = "/LOGO.png";
    PlaceOrderController placeOrderController;
    @FXML
    VBox vboxCart;
    @FXML
    private ImageView aimsImage;
    @FXML
    private Label pageTitle;
    @FXML
    private Label shippingFees;

    @FXML
    private Label labelAmount;

    @FXML
    private Label labelSubtotal;

    @FXML
    private Label labelVAT;

    @FXML
    private Button btnPlaceOrder;

    // stamp coupling: truyen doi tuong Stage
    // clean code: clean class: extract superclass
    // design pattern: template method
//    public CartScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupFunctionality();
//        } catch (IOException ex) {
//            LOGGER.info(ex.getMessage());
//            PopupScreen.error("Error when loading resources.");
//            setErrorMessage();
//        } catch (Exception ex) {
//            LOGGER.info(ex.getMessage());
//            PopupScreen.error(ex.getMessage());
//        }
//    }
    public CartScreenHandler(Stage stage, String screenPath)  throws IOException{
        super(stage, screenPath);
    }

    @Override
    protected void setupData(Object dto) throws Exception {
        return;
    }

    @Override
    public void requestToShowScreen(BaseScreenHandler previousScreen, BaseController bController) {
        try {
            setBController(bController);
            setPreviousScreen(previousScreen);
            setScreenTitle("Cart Screen");
            getBController().checkAvailabilityOfProduct();
            displayCartWithMediaAvailability();
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new ViewCartException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
        }
    }

    // clean code: tach method
    void setAimsImage() {
        File file = new File(ViewsConfig.IMAGE_PATH + bannerPath);
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);
        aimsImage.setOnMouseClicked(e -> {
            showHomeScreen();
        });
    }

    // clean code: tach method
    void setBtnPlaceOrder() {
        btnPlaceOrder.setOnMouseClicked(e -> {
            LOGGER.info("Place Order button clicked");
            try {
                requestToPlaceOrder();
            } catch (SQLException | IOException exp) {
                LOGGER.severe("Cannot place the order, see the logs");
                exp.printStackTrace();
                throw new PlaceOrderException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
            }
        });
    }

    /**
     * Temporal cohesion: cac cong viec btnPlaceOrder.setOnMouseClicked  va  aimsImage.setOnMouseClicked khong lien quan toi nhau</br>
     */
    protected void setupFunctionality() throws Exception {
        // fix relative image path caused by fxml
        // clean code: tach thanh cac khoi lenh
        /*
        // clean code: ko su dung string magic
//        File file = new File(ViewsConfig.IMAGE_PATH + "/Logo.png");
        File file = new File(ViewsConfig.IMAGE_PATH + bannerPath);
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // on mouse clicked, we back to home
        aimsImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });*/
        setAimsImage();

        // clean code: tach thanh cac khoi lenh nho
        /*
        // on mouse clicked, we start processing place order use case
        btnPlaceOrder.setOnMouseClicked(e -> {
            LOGGER.info("Place Order button clicked");
            try {
                requestToPlaceOrder();
            } catch (SQLException | IOException exp) {
                LOGGER.severe("Cannot place the order, see the logs");
                exp.printStackTrace();
                throw new PlaceOrderException(Arrays.toString(exp.getStackTrace()).replaceAll(", ", "\n"));
            }
        });*/
        setBtnPlaceOrder();
    }

    public ViewCartController getBController() {
        return (ViewCartController) super.getBController();
    }

    // stamp coupling: truyen doi tuong BaseScreenHandler
    // template method
//    public void requestToViewCart(BaseScreenHandler prevScreen) throws SQLException {
//        setPreviousScreen(prevScreen);
//        setScreenTitle("Cart Screen");
//        getBController().checkAvailabilityOfProduct();
//        displayCartWithMediaAvailability();
//        show();
//    }


    //clean code: tao ra cac function nho
    Order generateOrder() throws SQLException {
        try {
            if (getBController().getListCartMedia().size() == 0) {
                throw new PlaceOrderException("You don't have anything to place");
            }
            placeOrderController.placeOrder();

            displayCartWithMediaAvailability();

            return placeOrderController.createOrder();
        } catch (Exception e) {
            throw e;
        }
    }

    //clean code: tao ra cac function nho
    private void showShippingScreen(Order order) throws IOException {
        ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(
            this.stage, ViewsConfig.SHIPPING_SCREEN_PATH, order);
        shippingScreenHandler.showScreen(this, homeScreenHandler, placeOrderController);
    }

    /**
     * Communication cohesion: cac phuong thuc lien quan toi doi tuong shippingScreenHandler</br>
     * Template method
     */
    // clean code: gom cac doan code thanh cac function
    /*public void requestToPlaceOrder() throws SQLException, IOException {
        try {
            // create placeOrderController and process the order
            placeOrderController = PlaceOrderController.getInstance();
            if (placeOrderController.getListCartMedia().size() == 0) {
                PopupScreen.error("You don't have anything to place");
                return;
            }

            placeOrderController.placeOrder();

            // display available media
            displayCartWithMediaAvailability();

            // create order
            Order order = placeOrderController.createOrder();

            // display shipping form
            ShippingScreenHandler shippingScreenHandler = new ShippingScreenHandler(
                    this.stage, ViewsConfig.SHIPPING_SCREEN_PATH, order);

            //Template method
            shippingScreenHandler.showScreen(this, homeScreenHandler, placeOrderController);
//			shippingScreenHandler.setPreviousScreen(this);
//			shippingScreenHandler.setHomeScreenHandler(homeScreenHandler);
//			shippingScreenHandler.setScreenTitle("Shipping Screen");
//			shippingScreenHandler.setBController(placeOrderController);
//			shippingScreenHandler.show();

        } catch (MediaNotAvailableException e) {
            // if some media are not available then display cart and break usecase Place Order
            displayCartWithMediaAvailability();
        }
    }*/
    public void requestToPlaceOrder() throws SQLException, IOException {
        try {
            placeOrderController = PlaceOrderController.getInstance();
            Order order = generateOrder();
            showShippingScreen(order);
        } catch (PlaceOrderException e1) {
            PopupScreen.error(e1.getMessage());
        } catch (MediaNotAvailableException e) {
            displayCartWithMediaAvailability();
        }
    }

    public void updateCart() throws SQLException {
        getBController().checkAvailabilityOfProduct();
        displayCartWithMediaAvailability();
    }

    void updateCartAmount() {
        // calculate subtotal and amount
        int subtotal = getBController().getCartSubtotal();
        // clean code: dat ten bien ko ro rang (percent_vat da duoc tinh theo ti le %, khong can chia 100)
//        int vat = (int) ((ViewsConfig.PERCENT_VAT / 100) * subtotal);
        int vat = (int) ((ViewsConfig.PERCENT_VAT) * subtotal);
        int amount = subtotal + vat;
        LOGGER.info("amount" + amount);

        // update subtotal and amount of Cart
        labelSubtotal.setText(ViewsConfig.getCurrencyFormat(subtotal));
        labelVAT.setText(ViewsConfig.getCurrencyFormat(vat));
        labelAmount.setText(ViewsConfig.getCurrencyFormat(amount));
    }


    // clean code
    AnchorPane getContentMediaCart(CartItem cartItem) throws IOException {
        MediaHandler mediaCartScreen = new MediaHandler(ViewsConfig.CART_MEDIA_PATH);
        mediaCartScreen.setCartItem(cartItem);

        // design pattern: Observable
        mediaCartScreen.attach(this);

        return mediaCartScreen.getContent();
    }

    // clean code
    void updateVboxCard(List lstMedia) throws IOException {
        for (Object cm : lstMedia) {
            // display the attribute of vboxCart media
            AnchorPane content = getContentMediaCart((CartItem) cm);
            // add spinner
            vboxCart.getChildren().add(content);
        }
    }

    // clean code: tach thanh cac function nho
    /*
    private void displayCartWithMediaAvailability() {
        // clear all old cartMedia
        vboxCart.getChildren().clear();

        // get list media of cart after check availability
        List lstMedia = getBController().getListCartMedia();

        try {
            for (Object cm : lstMedia) {

                // display the attribute of vboxCart media
                CartItem cartItem = (CartItem) cm;
                MediaHandler mediaCartScreen = new MediaHandler(ViewsConfig.CART_MEDIA_PATH, this);
                mediaCartScreen.setCartItem(cartItem);

                // add spinner
                vboxCart.getChildren().add(mediaCartScreen.getContent());
            }
            // calculate subtotal and amount
            updateCartAmount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    private void displayCartWithMediaAvailability() {
        vboxCart.getChildren().clear();
        List lstMedia = getBController().getListCartMedia();
        try {
            updateVboxCard(lstMedia);
            updateCartAmount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Observable observable) {
        if (observable instanceof MediaHandler) update((MediaHandler) observable);
    }

    private void update(MediaHandler mediaHandler) {
        try {
            this.updateCart();
            this.updateCartAmount();
        } catch (SQLException exp) {
            exp.printStackTrace();
            throw new ViewCartException();
        }
    }
}
