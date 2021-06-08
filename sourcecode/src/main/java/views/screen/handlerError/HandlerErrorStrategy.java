package views.screen.handlerError;

import utils.Utils;
import views.screen.FXMLScreenHandler;

import java.util.logging.Logger;

public abstract class HandlerErrorStrategy {
    protected static final Logger LOGGER = Utils.getLogger(HandlerErrorStrategy.class.getName());

    private final FXMLScreenHandler context;

    public HandlerErrorStrategy(FXMLScreenHandler context){
        this.context = context;
    }

    public abstract void handlerError(Exception ex);
}
