package views.screen.popup;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;

import java.io.IOException;

// Temporal cohesion : class vừa chứa các hàm chỉ trạng thái thông báo, vừa chứa các hàm thực hiện các hành động như : close, show, setData...
public class PopupScreen extends BaseScreenHandler {

  @FXML
  ImageView icon;

  @FXML
  Label message;

  // stamp coupling: truyen doi tuong Stage
  public PopupScreen(Stage stage)throws IOException  {
    super(stage, ViewsConfig.POPUP_PATH, null);
  }

  // coupling: data -> chi phu thuoc mot so tham so

  /**
   * <h3><i>Communication cohesion : khai bao doi tuong popup va su dung</i></h3>
   * <h3><i>Logical cohesion : success() va error(); show() </i></h3>
   */
  private static PopupScreen popup(String message, String imagePath, Boolean undecorated) throws IOException {
    PopupScreen popup = new PopupScreen(new Stage());
    if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
    popup.message.setText(message);
    popup.setImage(imagePath);
    return popup;
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public static void success(String message) throws IOException {
    popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickgreen.png", true)
      .show(true);
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public static void error(String message) throws IOException  {
    popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickerror.png", false)
      .show(false);
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public static PopupScreen loading(String message) throws IOException {
    return popup(message, ViewsConfig.IMAGE_PATH + "/" + "loading.gif", true);
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public void setImage(String path) {
    super.setImage(icon, path);
  }

  public void show(Boolean autoClose) {
    super.show();
    if (autoClose) close(0.8);
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public void show(double time) {
    super.show();
    close(time);
  }

  // coupling: data -> chi phu thuoc mot so tham so
  public void close(double time) {
    PauseTransition delay = new PauseTransition(Duration.seconds(time));
    delay.setOnFinished(event -> stage.close());
    delay.play();
  }

  @Override
  protected void setupData(Object dto) throws Exception {

  }

  @Override
  protected void setupFunctionality() throws Exception {
  }

}
