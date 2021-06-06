package views.screen.home;

import common.exception.MediaNotAvailableException;
import common.exception.ViewCartException;
import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.AuthenticationController;
import controller.HomeController;
import controller.SessionInformation;
import controller.ViewCartController;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseNextScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.cart.CartScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

//Temporal cohesion: class chứa quá nhiều hàm thực hiện các chức năng khác nhau
public class HomeScreenHandler extends BaseScreenHandler implements Observer {

    public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

    final int BOOK_POSITION = 0;
    final int DVD_POSITION = 1;
    final int CD_POSITION = 2;

    @FXML
    private Label numMediaInCart;

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;

    @FXML
    private HBox hboxMedia;

    @FXML
    private Button btnLogin;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    private List<MediaHandler> homeItems;
    private AuthenticationController authenticationController;

    //stamp coupling
    // clean code: clean class: extract superclass
//    public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupData();
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
    public HomeScreenHandler(Stage stage, String screenPath) throws Exception {
        super(stage, screenPath, null);
    }

    // clean code: loai bo phuong thuc ko su dung
//    public Label getNumMediaCartLabel() {
//        return this.numMediaInCart;
//    }

    public HomeController getBController() {
        return (HomeController) super.getBController();
    }

    // stamp coupling
    // clean code: loai bo tham so ko su dung
//    protected void setupData(Object dto) throws Exception {
    @Override
    protected void setupData(Object dto) throws Exception {
        setBController(new HomeController());
        this.authenticationController = AuthenticationController.getInstance();
        try {
            List medium = getBController().getAllMedia();
            this.homeItems = new ArrayList<>();
            for (Object object : medium) {
                Media media = (Media) object;
                MediaHandler m = new MediaHandler(ViewsConfig.HOME_MEDIA_PATH, media);
                m.attach(this);
                this.homeItems.add(m);
            }
        } catch (SQLException | IOException e) {
            LOGGER.info("Errors occured: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void setupFunctionality() throws Exception {

        aimsImage.setOnMouseClicked(e -> {
            addMediaHome(this.homeItems);
        });

        cartImage.setOnMouseClicked(e -> {
            CartScreenHandler cartScreen;
            try {
                LOGGER.info("User clicked to view cart");
                cartScreen = new CartScreenHandler(this.stage, ViewsConfig.CART_SCREEN_PATH);
                cartScreen.showScreen(this, this, new ViewCartController());
            }
            catch (Exception e1) {
                throw new ViewCartException("HIHI"+Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
            }
        });
        addMediaHome(this.homeItems);
        // clean code: ko su dung magic_number
        addMenuItem(BOOK_POSITION, "Book", splitMenuBtnSearch);
        addMenuItem(DVD_POSITION, "DVD", splitMenuBtnSearch);
        addMenuItem(CD_POSITION, "CD", splitMenuBtnSearch);
    }

    @Override
    public void show() {
        if (authenticationController.isAnonymousSession()) {
            btnLogin.setText("Login");
            btnLogin.setOnMouseClicked(event -> redirectLoginScreen(event));
        } else {
            btnLogin.setText("User");
            btnLogin.setOnMouseClicked(event -> {
            });
        }

        numMediaInCart.setText(SessionInformation.getInstance().getCartInstance().getListCartMedia().size() + " media");
        super.show();
    }

    public void setImage() {
        // fix image path caused by fxml
        File file1 = new File(ViewsConfig.IMAGE_PATH + "/" + "Logo.png");
        Image img1 = new Image(file1.toURI().toString());
        aimsImage.setImage(img1);

        File file2 = new File(ViewsConfig.IMAGE_PATH + "/" + "cart.png");
        Image img2 = new Image(file2.toURI().toString());
        cartImage.setImage(img2);
    }

    //stamp coupling
    public void addMediaHome(List items) {
        ArrayList mediaItems = (ArrayList) ((ArrayList) items).clone();
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
        while (!mediaItems.isEmpty()) {
            hboxMedia.getChildren().forEach(node -> {
                int vid = hboxMedia.getChildren().indexOf(node);
                VBox vBox = (VBox) node;
                // clean code: ko su dung hang so
//                while (vBox.getChildren().size() < 3 && !mediaItems.isEmpty()) {
                while (vBox.getChildren().size() < Utils.SIZE_OF_HOME_ITEM && !mediaItems.isEmpty()) {
                    MediaHandler media = (MediaHandler) mediaItems.get(0);
                    vBox.getChildren().add(media.getContent());
                    mediaItems.remove(media);
                }
            });
            return;
        }
    }

    // clean code: tach thanh ham setLabelMenuItem
    Label getLabelMenuItem(String text, MenuButton menuButton) {
        Label label = new Label();
        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
        label.setText(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        return label;
    }

    private void addMenuItem(int position, String text, MenuButton menuButton) {
        MenuItem menuItem = new MenuItem();

        //clean code: tach thanh ham setLabelMenuItem
        Label label = getLabelMenuItem(text, menuButton);
        /*
        Label label = new Label();
        label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
        label.setText(text);
        label.setTextAlignment(TextAlignment.RIGHT);
        */
        menuItem.setGraphic(label);
        menuItem.setOnAction(e -> {
            // clean code: tach thanh function nho
            // empty home media
            /*hboxMedia.getChildren().forEach(node -> {
                VBox vBox = (VBox) node;
                vBox.getChildren().clear();
            });*/
            emptyHomeMedia();

            // clean code: tach thanh function nho
            // filter only media with the choosen category
           /* List filteredItems = new ArrayList<>();
            homeItems.forEach(me -> {
                MediaHandler media = (MediaHandler) me;
                // clean code: tranh truy cap qua sau vao doi tuong
//                if (media.getMedia().getTitle().toLowerCase().startsWith(text.toLowerCase())) {
                if (media.getMedia().includeTitle(text)) {
                    filteredItems.add(media);
                }
            });*/;
            List filteredItems = filterHomeMediaItems(text);

            // fill out the home with filted media as category
            addMediaHome(filteredItems);
        });
        menuButton.getItems().add(position, menuItem);
    }

    @Override
    //stamp coupling
    /**
     * SOLID: OCP vi observable phu thuoc truc tiep vao kieu MediaHandler
     * */
    public void update(Observable observable) {
        if (observable instanceof MediaHandler) update((MediaHandler) observable);
    }

    //stamp coupling
    private void update(MediaHandler mediaHandler) {
        int requestQuantity = mediaHandler.getRequestQuantity();
        Media media = mediaHandler.getMedia();

        try {
            if (requestQuantity > media.getQuantity()) throw new MediaNotAvailableException();
            Cart cart = SessionInformation.getInstance().getCartInstance();
            // if media already in cart then we will increase the quantity by 1 instead of create the new cartMedia
            CartItem mediaInCart = getBController().checkMediaInCart(media);
            if (mediaInCart != null) {
                mediaInCart.setQuantity(mediaInCart.getQuantity() + 1);
            } else {
                // clean code: xoa phuong thuc khong su dung
                // old: CartItem cartItem = new CartItem(media, cart, requestQuantity, media.getPrice());
                CartItem cartItem = new CartItem(media, requestQuantity, media.getPrice());
                cart.addCartMedia(cartItem);
                LOGGER.info("Added " + cartItem.getQuantity() + " " + media.getTitle() + " to cart");
            }

            // subtract the quantity and redisplay
            media.setQuantity(media.getQuantity() - requestQuantity);
            numMediaInCart.setText(cart.getTotalMedia() + " media");
            PopupScreen.success("The media " + media.getTitle() + " added to Cart");
        } catch (MediaNotAvailableException exp) {
            try {
                String message = "Not enough media:\nRequired: " + requestQuantity + "\nAvail: " + media.getQuantity();
                LOGGER.severe(message);
                PopupScreen.error(message);
            } catch (Exception e) {
                LOGGER.severe("Cannot add media to cart: ");
            }

        } catch (Exception exp) {
            LOGGER.severe("Cannot add media to cart: ");
            exp.printStackTrace();
        }
    }

    @FXML
    void redirectLoginScreen(MouseEvent event) {
        try {
            BaseNextScreenHandler loginScreen = new LoginScreenHandler(this.stage, ViewsConfig.LOGIN_SCREEN_PATH);

            //template method
//            loginScreen.setHomeScreenHandler(this);
//            loginScreen.setBController(this.authenticationController);
//            loginScreen.show();
            loginScreen.showScreen(null, this, this.authenticationController);
        } catch (Exception ex) {
            try {
                PopupScreen.error("Cant trigger Login");
            } catch (Exception ex1) {
                LOGGER.severe("Cannot login");
                ex.printStackTrace();
            }
        }
    }


    void emptyHomeMedia() {
        hboxMedia.getChildren().forEach(node -> {
            VBox vBox = (VBox) node;
            vBox.getChildren().clear();
        });
    }

    List filterHomeMediaItems(String text) {
        List filteredItems = new ArrayList<>();
        homeItems.forEach(me -> {
            MediaHandler media = me;
            // clean code: tranh truy cap qua sau vao doi tuong
//                if (media.getMedia().getTitle().toLowerCase().startsWith(text.toLowerCase())) {
            if (media.getMedia().includeTitle(text)) {
                filteredItems.add(media);
            }
        });
        return filteredItems;
    }
}
