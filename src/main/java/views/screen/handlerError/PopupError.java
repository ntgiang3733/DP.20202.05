package views.screen.handlerError;

import views.screen.FXMLScreenHandler;
import views.screen.popup.PopupScreen;

public class PopupError extends HandlerErrorStrategy{
    public PopupError(FXMLScreenHandler context) {
        super(context);
    }

    @Override
    public void handlerError(Exception ex) {
        LOGGER.info(ex.getMessage());
        try{
            PopupScreen.error("Error when loading resources." + this.getClass().getName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
