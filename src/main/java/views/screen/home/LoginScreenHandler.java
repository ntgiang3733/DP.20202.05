package views.screen.home;

import controller.AuthenticationController;
import controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseNextScreenHandler;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;


public class LoginScreenHandler extends BaseNextScreenHandler {

    public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    // clean code: clean class: extract superclass
//    public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupData(null);
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
    public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath, null);
    }


    @Override
    protected void setupData(Object dto) throws Exception {
    }


    @Override
    protected void setupFunctionality() throws Exception {
    }

    @Override
    public void requestToShowScreen(BaseScreenHandler previousScreen, BaseController bController) {
        setBController(bController);
    }

    public AuthenticationController getBController() {
        return (AuthenticationController) super.getBController();
    }

    @FXML
    void login(MouseEvent event) throws IOException, InterruptedException, SQLException {
        try {
            getBController().login(email.getText(), password.getText());
            PopupScreen.success("Login Successfully!");
            backToHomeScreen(event);
        } catch (Exception ex) {
            PopupScreen.error(ex.getMessage());
        }
    }

    //Coincidental Cohesion: hàm này đc gọi nhiều lần nên để ra class Util
    @FXML
    void backToHomeScreen(MouseEvent event) throws IOException, InterruptedException, SQLException {
        this.showHomeScreen();
    }
}
