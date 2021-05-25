package views.screen.intro;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class IntroScreenHandler extends BaseScreenHandler {

  private static final Logger LOGGER = Utils.getLogger(IntroScreenHandler.class.getName());


  @FXML
  ImageView logo;

  // clean code: clean class: extract superclass
//    public IntroScreenHandler(Stage stage, String screenPath) throws IOException {
//        super(stage, screenPath);
//        try {
//            setupData(null);
//            setupFunctionality();
//        } catch (IOException ex) {
//            LOGGER.info(ex.getMessage());
//            PopupScreen.error("Error when loading resources.");
//            setErrorMessage();
//        } catch (Exception ex) {
//            LOGGER.info(ex.getMessage());
//            PopupScreen.error(ex.getMessage());
//        }
//    }
  public IntroScreenHandler(Stage stage, String screenPath) throws IOException {
    super(stage, screenPath, null);
  }

  //stamp coupling: truyen doi tuong Object

  @Override
  protected void setupData(Object dto) throws Exception {
      return;
  }

  @Override
  protected void setupFunctionality() throws Exception {
    File file = new File("src/main/resources/assets/images/Logo.png");
    Image image = new Image(file.toURI().toString());
    logo.setImage(image);
  }
}
