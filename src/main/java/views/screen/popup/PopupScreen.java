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


public class PopupScreen extends BaseScreenHandler {

    @FXML
    ImageView icon;

    @FXML
    Label message;

    public PopupScreen(Stage stage) throws IOException{
        super(stage, ViewsConfig.POPUP_PATH);
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    private static PopupScreen popup(String message, String imagePath, Boolean undecorated) throws IOException{
        PopupScreen popup = new PopupScreen(new Stage());
        if (undecorated) popup.stage.initStyle(StageStyle.UNDECORATED);
        popup.message.setText(message);
        popup.setImage(imagePath);
        return popup;
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public static void success(String message) throws IOException{
        popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickgreen.png", true)
                .show(true);
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public static void error(String message) throws IOException{
        popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickerror.png", false)
                .show(false);
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public static PopupScreen loading(String message) throws IOException{
        return popup(message, ViewsConfig.IMAGE_PATH + "/" + "loading.gif", true);
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public void setImage(String path) {
        super.setImage(icon, path);
    }

    public void show(Boolean autoClose) {
        super.show();
        if (autoClose) close(0.8);
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public void show(double time) {
        super.show();
        close(time);
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public void close(double time) {
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }

    protected void setupData(Object dto) throws Exception {
    }

    protected void setupFunctionality() throws Exception {
    }
}
