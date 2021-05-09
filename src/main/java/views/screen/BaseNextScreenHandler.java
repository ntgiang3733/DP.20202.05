package views.screen;

import controller.BaseController;
import javafx.stage.Stage;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;

public abstract class BaseNextScreenHandler extends BaseScreenHandler {

    protected BaseNextScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
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

    protected BaseNextScreenHandler(Stage stage, String screenPath,
                                    Object dto) throws IOException {
        super(stage, screenPath);
        try {
            setupData(dto);
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

    protected abstract void setupData(Object dto) throws Exception;

    abstract protected void setTitleScreenToShow();

    @Override
    protected void setupData() throws Exception {
    }

    // template method
    public void showScreen(BaseNextScreenHandler previousScreen, HomeScreenHandler homeScreenHandler, BaseController bController) {
        setTitleScreenToShow();
        super.setPreviousScreen(previousScreen);
        super.setHomeScreenHandler(homeScreenHandler);
        super.setBController(bController);
        super.show();
    }
}
