package views.screen.handlerError;

import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.popup.PopupScreen;

import java.util.logging.Logger;

public class LogError extends HandlerErrorStrategy{
    public LogError(FXMLScreenHandler context) {
        super(context);
    }

    @Override
    public void handlerError(Exception ex) {

        LOGGER.info(ex.getMessage());
    }
}
