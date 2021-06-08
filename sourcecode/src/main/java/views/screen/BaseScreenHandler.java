package views.screen;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

public abstract class BaseScreenHandler extends FXMLScreenHandler {

    protected static final Logger LOGGER = Utils.getLogger(BaseScreenHandler.class.getName());
    protected final Stage stage;
    protected BaseScreenHandler prev;
    protected HomeScreenHandler homeScreenHandler;
    protected Hashtable<String, String> messages;
    protected BaseController bController;
    private Scene scene;

    //    // clean code: clean class: extract superclass
//    protected BaseScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(screenPath);
//        this.stage = stage;
//    }
    protected BaseScreenHandler(Stage stage, String screenPath,
                                Object dto) throws IOException {
        super(screenPath);
        this.stage = stage;
        try{
            setupData(dto);
            setupFunctionality();
        }
        catch (IOException ex){
            throw ex;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    protected abstract void setupData(Object dto) throws Exception;

    protected abstract void setupFunctionality() throws Exception;

    public void setPreviousScreen(BaseScreenHandler prev) {

        this.prev = prev;
    }

    // clean code: loai bo phuong thuc ko su dung
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

    public BaseController getBController() {
        return this.bController;
    }

    public void setBController(BaseController bController) {
        this.bController = bController;
    }

    // clean code: loai bo phuong thuc ko su dung
   /* public void forward(Hashtable messages) {
        this.messages = messages;
    }*/

    //stamp coupling: truyen doi tuong HomeScreenHandler
    public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
        this.homeScreenHandler = HomeScreenHandler;
    }

    public void showHomeScreen() {
        this.homeScreenHandler.show();
    }

}
