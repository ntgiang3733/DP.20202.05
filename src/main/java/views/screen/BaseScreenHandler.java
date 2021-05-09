package views.screen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import controller.AuthenticationController;
import controller.BaseController;
import entity.order.Order;
import entity.shipping.ShippingConfigs;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

public abstract class BaseScreenHandler extends FXMLScreenHandler {

    protected static final Logger LOGGER = Utils.getLogger(BaseScreenHandler.class.getName());


    private Scene scene;
    protected BaseScreenHandler prev;
    protected final Stage stage;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
    protected BaseController bController;

//    // cleancode: clean class: extract superclass
//    protected BaseScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(screenPath);
//        this.stage = stage;
//    }
    protected BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
        try {
            setupData();
            setupFunctionality();
        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error("Error when loading resources.");
            setErrorMessage();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }

    protected abstract void setupData() throws Exception;
    protected abstract void setupFunctionality() throws Exception;

    public void setPreviousScreen(BaseScreenHandler prev) {

        this.prev = prev;
    }
    // cleancode: loai bo phuong thuc ko su dung
    /*
    public BaseScreenHandler getPreviousScreen() {
        return this.prev;
    }
*/
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void setScreenTitle(String string) {
        this.stage.setTitle(string);
    }

    public void setBController(BaseController bController) {
        this.bController = bController;
    }

    public BaseController getBController() {
        return this.bController;
    }

    // cleancode: loai bo phuong thuc ko su dung
   /* public void forward(Hashtable messages) {
        this.messages = messages;
    }*/

    //stamp coupling: truyen doi tuong HomeScreenHandler
    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }

}
