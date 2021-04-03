package views.screen;

import controller.BaseController;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;

import java.io.IOException;

public abstract class BaseNextScreenHandler extends BaseScreenHandler {

    protected BaseNextScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    abstract protected void setTitleScreenToShow();

    // template method
    public void showScreen( BaseNextScreenHandler previousScreen, HomeScreenHandler homeScreenHandler, BaseController bController) {
        setTitleScreenToShow();
        setPreviousScreen(previousScreen);
        setHomeScreenHandler(homeScreenHandler);
        setBController(bController);
        show();
    }
}
