package views.screen.shipping;

import common.exception.InvalidDeliveryInfoException;
import controller.BaseController;
import controller.PlaceOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.CalculatorShippingFee;
import entity.shipping.DeliveryInfo;
import entity.shipping.ShippingConfigs;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseNextScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.invoice.InvoiceScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ShippingScreenHandler extends BaseNextScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(ShippingScreenHandler.class.getName());

    @FXML
    private Label screenTitle;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField instructions;

    @FXML
    private ComboBox<String> province;

    private Order order;

    //stamp coupling
    // clean code: clean class: extract superclass
//    public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupData(order);
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
    public ShippingScreenHandler(Stage stage, String screenPath, Order order)throws IOException  {
        super(stage, screenPath, order);
    }

    //stamp coupling
    @Override
    protected void setupData(Object dto) throws Exception {
        this.order = (Order) dto;
        this.province.getItems().addAll(ShippingConfigs.PROVINCES);
        this.province.getSelectionModel().select(ShippingConfigs.RUSH_SUPPORT_PROVINCES_INDEX[0]);
    }

    @Override
    public void requestToShowScreen(BaseScreenHandler previousScreen, BaseController bController) {
        setPreviousScreen(previousScreen);
        setScreenTitle("Shipping Screen");
        setBController(bController);
    }

    @Override
    protected void setupFunctionality() throws Exception {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
        name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                content.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

    }

    /**
     * Procedural cohesion : submitDeliveryInfo() sau do den preprocessDeliveryInfo() </br>
     */

    @FXML
    void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException,Exception {
        // validate delivery info and prepare order info
        preprocessDeliveryInfo();

        // create invoice screen
        Invoice invoice = getBController().createInvoice(order);
        BaseNextScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, ViewsConfig.INVOICE_SCREEN_PATH, invoice);

        // template method
//		InvoiceScreenHandler.setPreviousScreen(this);
//		InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
//		InvoiceScreenHandler.setBController(getBController());
//		InvoiceScreenHandler.setScreenTitle("Invoice Screen");
//		InvoiceScreenHandler.show();
        InvoiceScreenHandler.showScreen(this, homeScreenHandler, getBController());
    }

    // clean code: tao ra mot doi tuong message rieng
//    public void preprocessDeliveryInfo() throws IOException, InterruptedException {
//        // add info to messages
//        HashMap<String, String> messages = new HashMap<>();
//        messages.put("name", name.getText());
//        messages.put("phone", phone.getText());
//        messages.put("address", address.getText());
//        messages.put("instructions", instructions.getText());
//        messages.put("province", province.getValue());
//        DeliveryInfo deliveryInfo;
//        try {
//            // process and validate delivery info
//            deliveryInfo = getBController().processDeliveryInfo(messages);
//        } catch (InvalidDeliveryInfoException e) {
//            // TODO: implement pop up screen
//            throw new InvalidDeliveryInfoException(e.getMessage());
//        }
//
//        order.setDeliveryInfo(deliveryInfo);
//    }
    public void preprocessDeliveryInfo() throws IOException, InterruptedException {
        // add info to messages
        DeliveryInfoObj message = new DeliveryInfoObj(name.getText(), phone.getText(), address.getText(), instructions.getText(), province.getValue());
        DeliveryInfo deliveryInfo;
        try {
            // process and validate delivery info
            deliveryInfo = getBController().processDeliveryInfo(message);
        } catch (InvalidDeliveryInfoException e) {
            // TODO: implement pop up screen
            throw new InvalidDeliveryInfoException(e.getMessage());
        }

        order.setDeliveryInfo(deliveryInfo);
    }

    public PlaceOrderController getBController() {
        return (PlaceOrderController) super.getBController();
    }

    // clean code: loai bo phuong thuc ko su dung
//	public void notifyError(){
//		// TODO: implement later on if we need
//	}


}
