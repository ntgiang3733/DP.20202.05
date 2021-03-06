package views.screen.payment;

import controller.BaseController;
import controller.PaymentController;
import entity.invoice.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseNextScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.ResponseMessage;
import views.screen.ViewsConfig;

import java.io.IOException;
import java.util.logging.Logger;

public class PaymentScreenHandler extends BaseNextScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());

    @FXML
    private Button btnConfirmPayment;

    @FXML
    private ImageView loadingImage;

    private Invoice invoice;

    @FXML
    private Label pageTitle;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField holderName;

    @FXML
    private TextField expirationDate;

    @FXML
    private TextField securityCode;

    // stamp coupling: truyen doi tuong Stage, Invoice

    /**
     * <h3><i>Temporal cohesion: 2 phuong thuc setupData() va setupFunctionality() chi lien quan toi thu tu thuc hien  </i></h3>
     */
    // clean code: clean class: extract superclass
//    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupData(invoice);
//            setupFunctionality();
//        } catch (IOException ex) {
//            LOGGER.info(ex.getMessage());
//            PopupScreen.error("Error when loading resources.");
//        } catch (Exception ex) {
//            LOGGER.info(ex.getMessage());
//            PopupScreen.error(ex.getMessage());
//        }
//    }
    public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException  {
        super(stage, screenPath, invoice);
    }

    // stamp coupling: truyen doi tuong Object
    @Override
    protected void setupData(Object dto) throws Exception {
        this.invoice = (Invoice) dto;
    }

    @Override
    public void requestToShowScreen(BaseScreenHandler previousScreen, BaseController bController) {
        setPreviousScreen(previousScreen);
        setBController(bController);
        setScreenTitle("Payment Screen");
    }

    @Override
    protected void setupFunctionality() throws Exception {
        btnConfirmPayment.setOnMouseClicked(e -> {
            try {
                confirmToPayOrder();
                ((PaymentController) getBController()).emptyCart();
            } catch (Exception exp) {
                System.out.println(exp.getStackTrace());
            }
        });
    }

    /**
     * <h3><i>Communication cohesion</i></h3>
     */
    void confirmToPayOrder() throws IOException {
        String contents = "pay order";
        PaymentController ctrl = (PaymentController) getBController();

        // clean code: tao doi tuong responseMessage
//        Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
//                expirationDate.getText(), securityCode.getText());

        ResponseMessage response = ctrl.payOrder(invoice.getAmount(), contents, cardNumber.getText(), holderName.getText(),
            expirationDate.getText(), securityCode.getText());

        BaseNextScreenHandler resultScreen = new ResultScreenHandler(this.stage, ViewsConfig.RESULT_SCREEN_PATH, response);

        // template method
		/*resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();*/
        resultScreen.showScreen(this, homeScreenHandler, null);
    }
}
