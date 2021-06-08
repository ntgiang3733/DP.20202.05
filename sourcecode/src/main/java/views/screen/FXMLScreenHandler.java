package views.screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.Utils;
import views.screen.handlerError.HandlerErrorStrategy;
import views.screen.handlerError.PopupError;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FXMLScreenHandler {
    protected static final Logger LOGGER = Utils.getLogger(FXMLScreenHandler.class.getName());
    @FXML
    protected Label errorMessage;
    protected FXMLLoader loader;
    protected AnchorPane content;

    // data
    public FXMLScreenHandler(String screenPath) throws IOException {
        HandlerErrorStrategy handlerErrorStrategy = new PopupError(this);
        try {
            this.loader = new FXMLLoader(getClass().getResource(screenPath));
            // Set this class as the controller
            this.loader.setController(this);
            this.content = loader.load();
        } catch (IOException ioe) {
            handlerErrorStrategy.handlerError(ioe);
            throw ioe;
        } catch (Exception ex) {
            handlerErrorStrategy.handlerError(ex);
        }
    }


    public AnchorPane getContent() {
        return this.content;
    }

    // clean code: loai bo phuong thuc ko su dung
/*	public FXMLLoader getLoader() {
		return this.loader;
	}*/

    public void setImage(ImageView imv, String path) {
        File file = new File(path);
        Image img = new Image(file.toURI().toString());
        imv.setImage(img);
    }

}
