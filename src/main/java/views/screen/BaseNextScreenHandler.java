package views.screen;

import controller.BaseController;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;

public abstract class BaseNextScreenHandler extends BaseScreenHandler {

    protected BaseNextScreenHandler(Stage stage, String screenPath, Object dto) throws IOException {
        super(stage, screenPath, dto);
    }

    protected BaseNextScreenHandler(Stage stage, String screenPath)throws IOException   {
        super(stage, screenPath, null);
    }

    abstract public void requestToShowScreen(BaseScreenHandler previousScreen, BaseController bController);

    // template method
    public void showScreen(BaseScreenHandler previousScreen, HomeScreenHandler homeScreenHandler, BaseController bController) {
        super.setHomeScreenHandler(homeScreenHandler);
        requestToShowScreen( previousScreen,  bController);
        super.show();
    }
}
