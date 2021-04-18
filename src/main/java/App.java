import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.home.*;
import views.screen.intro.IntroScreenHandler;

//temporal cohesion: can goi fadeOut.setOnFinished truoc khi goi fadeOut.play()

public class App extends Application {

    static final int TIME_TIMEOUT_FADE_IN = 2;

    private FadeTransition fadeIn;
    private FadeTransition fadeOut;
    private BaseScreenHandler introScreen;

    // cleancode: tach thanh cac function nho

    // cleancode: tach thanh cac function nho
    void initIntroScreen(Stage primaryStage) throws IOException {
        // initialize the scene
        introScreen = new IntroScreenHandler(primaryStage, ViewsConfig.INTRO_SCREEN_PATH);
        introScreen.show();
    }

    // cleancode: tach thanh cac function nho
    void initFaceIn() {
        fadeIn = new FadeTransition(Duration.seconds(TIME_TIMEOUT_FADE_IN), introScreen.getContent());
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });
    }

    // cleancode: tach thanh cac function nho
    void initFadeOut(Stage primaryStage) {
        fadeOut = new FadeTransition(Duration.seconds(1), introScreen.getContent());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);
        fadeOut.setOnFinished((e) -> {
            try {
                HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, ViewsConfig.HOME_PATH);
                homeHandler.setScreenTitle("Home Screen");
                homeHandler.setImage();
                homeHandler.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    ImageView logo;

    @Override
    public void start(Stage primaryStage) {
        try {

            // cleancode: tach thanh cac function nho
            initIntroScreen(primaryStage);
			/*// initialize the scene
			BaseScreenHandler introScreen = new IntroScreenHandler(primaryStage, ViewsConfig.INTRO_SCREEN_PATH);
			introScreen.show();*/

            // cleancode: tach thanh cac function nho
            initFaceIn();
			/*
			// Load splash screen with fade in effect
			FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), introScreen.getContent());
			fadeIn.setFromValue(0);
			fadeIn.setToValue(1);
			fadeIn.setCycleCount(1);*/

            // cleancode: tach thanh cac function nho
            initFadeOut(primaryStage);
            // Finish splash with fade out effect
			/*FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), introScreen.getContent());
			fadeOut.setFromValue(1);
			fadeOut.setToValue(0);
			fadeOut.setCycleCount(1);*/

            // After fade in, start fade out
            fadeIn.play();
            // cleancode
			/*
			fadeIn.setOnFinished((e) -> {
				fadeOut.play();
			});*/

            //cleancode
            // After fade out, load actual content
            /*fadeOut.setOnFinished((e) -> {
                try {
                    HomeScreenHandler homeHandler = new HomeScreenHandler(primaryStage, ViewsConfig.HOME_PATH);
                    homeHandler.setScreenTitle("Home Screen");
                    homeHandler.setImage();
                    homeHandler.show();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
