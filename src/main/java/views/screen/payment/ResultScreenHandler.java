package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseNextScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.ResponseMessage;
import views.screen.popup.PopupScreen;

public class ResultScreenHandler extends BaseNextScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());

    private String result;
    private String message;

    // stamp coupling: truyen doi tuong Stage, cau truc Map<String, String>
    // cleancode: extract superclass
//    public ResultScreenHandler(Stage stage, String screenPath, ResponseMessage response) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupData(response);
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
    public ResultScreenHandler(Stage stage, String screenPath, ResponseMessage response) throws IOException {
        super(stage, screenPath, response);
    }

    // stamp coupling: truyen doi tuong Object
    // cleancode: Tao doi tuong response message
/*
    protected void setupData(Object dto) throws Exception {
        Map<String, String> response = (Map<String, String>) dto;
        resultLabel.setText(response.get("RESULT"));
        messageLabel.setText(response.get("MESSAGE"));
    }*/
    @Override
    protected void setupData(Object dto) throws Exception {
        ResponseMessage response = (ResponseMessage) dto;
        resultLabel.setText(response.getResult());
        messageLabel.setText(response.getMessage());
    }

    @Override
    protected void setupFunctionality() throws Exception {
        return;
    }

    @FXML
    private Label pageTitle;

    @FXML
    private Label resultLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label messageLabel;


    @FXML
    void confirmPayment(MouseEvent event) throws IOException { // stamp coupling: truyen doi tuong MouseEvent
        this.showHomeScreen();
    }

    @Override
    protected void setTitleScreenToShow() {
        setScreenTitle("Result Screen");
    }
}
