package views.screen.home;

import common.interfaces.Observable;
import common.interfaces.Observer;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.ViewsConfig;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MediaHandler extends FXMLScreenHandler implements Observable {

    private static final Logger LOGGER = Utils.getLogger(MediaHandler.class.getName());
    private final Media media;
    private final List<Observer> observerList;
    @FXML
    protected ImageView mediaImage;
    @FXML
    protected Label mediaTitle;
    @FXML
    protected Label mediaPrice;
    @FXML
    protected Label mediaAvail;
    @FXML
    protected Spinner<Integer> spinnerChangeNumber;
    @FXML
    protected Button addToCartBtn;

    public MediaHandler(String screenPath, Media media) throws SQLException, IOException {
        super(screenPath);
        this.media = media;
        this.observerList = new ArrayList<>();
        addToCartBtn.setOnMouseClicked(event -> {
            notifyObservers();
        });
        setMediaInfo();
    }

    Media getMedia() {
        return media;
    }

    int getRequestQuantity() {
        return spinnerChangeNumber.getValue();
    }


    // clean code: tach thanh cac function nho
    void setCoverImage() {
        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());
        mediaImage.setFitHeight(160);
        mediaImage.setFitWidth(152);
        mediaImage.setImage(image);

        setImage(mediaImage, media.getImageURL());
    }

    // clean code: tach thanh ham setCoverImage
//    private void setMediaInfo() throws SQLException {
//       // set the cover image of media
//        File file = new File(media.getImageURL());
//        Image image = new Image(file.toURI().toString());
//        mediaImage.setFitHeight(160);
//        mediaImage.setFitWidth(152);
//        mediaImage.setImage(image);
//
//        mediaTitle.setText(media.getTitle());
//        mediaPrice.setText(ViewsConfig.getCurrencyFormat(media.getPrice()));
//        mediaAvail.setText(Integer.toString(media.getQuantity()));
//        spinnerChangeNumber.setValueFactory(
//            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
//        );
//
//        setImage(mediaImage, media.getImageURL());
//    }
    private void setMediaInfo() throws SQLException {
        setCoverImage();
        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(ViewsConfig.getCurrencyFormat(media.getPrice()));
        mediaAvail.setText(Integer.toString(media.getQuantity()));
        spinnerChangeNumber.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1)
        );
    }

    @Override
    //stamp coupling
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    //stamp coupling
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(observer -> observer.update(this));
    }
}
