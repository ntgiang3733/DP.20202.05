package views.screen;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class FXMLScreenHandler {
	@FXML
	protected Label errorMessage;

	protected FXMLLoader loader;
	protected AnchorPane content;

	// data
	public FXMLScreenHandler(String screenPath) throws IOException {
//		this.errorMessage.setText("Done!");
		this.loader = new FXMLLoader(getClass().getResource(screenPath));
		// Set this class as the controller
		this.loader.setController(this);
		this.content = (AnchorPane) loader.load();
	}

	public AnchorPane getContent() {
		return this.content;
	}

	public FXMLLoader getLoader() {
		return this.loader;
	}

	public void setImage(ImageView imv, String path){
		File file = new File(path);
		Image img = new Image(file.toURI().toString());
		imv.setImage(img);
	}

	public void setErrorMessage(){
		this.errorMessage.setText("Not found " + getClass());
	}
}
