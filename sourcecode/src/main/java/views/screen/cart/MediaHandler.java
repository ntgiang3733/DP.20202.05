package views.screen.cart;

import common.exception.MediaUpdateException;
import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.SessionInformation;
import entity.cart.CartItem;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.ViewsConfig;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class MediaHandler extends FXMLScreenHandler implements Observable {

  private static final Logger LOGGER = Utils.getLogger(MediaHandler.class.getName());

  @FXML
  protected HBox hboxMedia;

  @FXML
  protected ImageView image;

  @FXML
  protected VBox description;

  @FXML
  protected Label labelOutOfStock;

  @FXML
  protected VBox spinnerFX;

  @FXML
  protected Label title;

  @FXML
  protected Label price;

  @FXML
  protected Label currency;

  @FXML
  protected Button btnDelete;

  private CartItem cartItem;
  private Spinner<Integer> spinner;
  //    private CartScreenHandler cartScreen;
  private int numOfProd;
  private ArrayList<Observer> listObserver = new ArrayList<Observer>();

  // stamp coupling: truyen doi tuong CartScreenHandler
  public MediaHandler(String screenPath) throws IOException {
    super(screenPath);

    listObserver = new ArrayList<Observer>();
//    this.cartScreen = cartScreen;

    hboxMedia.setAlignment(Pos.CENTER);
  }

  // stamp coupling: truyen doi tuong CartItem
  public void setCartItem(CartItem cartItem) {
    this.cartItem = cartItem;
    setMediaInfo();
  }

  // clean code: tach thanh cac function nho
  void setImageView() {
    File file = new File(cartItem.getMedia().getImageURL());
    Image im = new Image(file.toURI().toString());
    image.setImage(im);
    image.setPreserveRatio(false);
    image.setFitHeight(110);
    image.setFitWidth(92);
  }

  void setDeleteButton() {
    btnDelete.setFont(ViewsConfig.REGULAR_FONT);
    btnDelete.setOnMouseClicked(e -> {
      SessionInformation.getInstance().getCartInstance().removeCartMedia(cartItem); // update user cart
      notifyObservers();
      LOGGER.info("Deleted " + cartItem.getMedia().getTitle() + " from the cart");
    });
  }

  private void setMediaInfo() {
    title.setText(cartItem.getMedia().getTitle());
    price.setText(ViewsConfig.getCurrencyFormat(cartItem.getPrice()));

    // clean code: tach thanh cac function
    setImageView();
		/*File file = new File(cartItem.getMedia().getImageURL());
		Image im = new Image(file.toURI().toString());
		image.setImage(im);
		image.setPreserveRatio(false);
		image.setFitHeight(110);
		image.setFitWidth(92);*/

    // add delete button
    // clean code: tach thanh funtion setDeleteButton
    setDeleteButton();
		/*btnDelete.setFont(ViewsConfig.REGULAR_FONT);
		btnDelete.setOnMouseClicked(e -> {
			try {
				SessionInformation.getInstance().getCartInstance().removeCartMedia(cartItem); // update user cart
				cartScreen.updateCart(); // re-display user cart
				LOGGER.info("Deleted " + cartItem.getMedia().getTitle() + " from the cart");
			} catch (SQLException exp) {
				exp.printStackTrace();
				throw new ViewCartException();
			}
		});*/

    initializeSpinner();
  }

  // design pattern: Observable
//    private void initializeSpinner() {
//        SpinnerValueFactory<Integer> valueFactory = //
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, cartItem.getQuantity());
//        spinner = new Spinner<Integer>(valueFactory);
//        spinner.setOnMouseClicked(e -> {
//            try {
//                int numOfProd = this.spinner.getValue();
//                int remainQuantity = cartItem.getMedia().getQuantity();
//                LOGGER.info("NumOfProd: " + numOfProd + " -- remainOfProd: " + remainQuantity);
//                if (numOfProd > remainQuantity) {
//                    LOGGER.info("product " + cartItem.getMedia().getTitle() + " only remains " + remainQuantity + " (required " + numOfProd + ")");
//                    labelOutOfStock.setText("Sorry, Only " + remainQuantity + " remain in stock");
//                    spinner.getValueFactory().setValue(remainQuantity);
//                    numOfProd = remainQuantity;
//                }
//
//                // update quantity of mediaCart in useCart
//                cartItem.setQuantity(numOfProd);
//
//                // update the total of mediaCart
//                price.setText(ViewsConfig.getCurrencyFormat(numOfProd * cartItem.getPrice()));
//
//                // update subtotal and amount of Cart
//                cartScreen.updateCartAmount();
//
//            } catch (SQLException e1) {
//                throw new MediaUpdateException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
//            }
//
//        });
//        spinnerFX.setAlignment(Pos.CENTER);
//        spinnerFX.getChildren().add(this.spinner);
//    }
  private void initializeSpinner() {
    SpinnerValueFactory<Integer> valueFactory = //
      new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, cartItem.getQuantity());
    spinner = new Spinner<Integer>(valueFactory);
    spinner.setOnMouseClicked(e -> {
      try {
        this.numOfProd = this.spinner.getValue();
        int remainQuantity = cartItem.getMedia().getQuantity();
        LOGGER.info("NumOfProd: " + numOfProd + " -- remainOfProd: " + remainQuantity);
        if (numOfProd > remainQuantity) {
          LOGGER.info("product " + cartItem.getMedia().getTitle() + " only remains " + remainQuantity + " (required " + numOfProd + ")");
          labelOutOfStock.setText("Sorry, Only " + remainQuantity + " remain in stock");
          spinner.getValueFactory().setValue(remainQuantity);
          numOfProd = remainQuantity;
        }

        // update quantity of mediaCart in useCart
        cartItem.setQuantity(numOfProd);
        // update the total of mediaCart
        price.setText(ViewsConfig.getCurrencyFormat(numOfProd * cartItem.getPrice()));

        notifyObservers();

      } catch (SQLException e1) {
        throw new MediaUpdateException(Arrays.toString(e1.getStackTrace()).replaceAll(", ", "\n"));
      }

    });
    spinnerFX.setAlignment(Pos.CENTER);
    spinnerFX.getChildren().add(this.spinner);
  }

  public int getNumOfProd() {
    return this.numOfProd;
  }

  @Override
  public void attach(Observer observer) {
    if (observer != null)
      listObserver.add(observer);
  }

  @Override
  public void remove(Observer observer) {
    listObserver.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : listObserver) {
      observer.update(this);
    }
  }
}
