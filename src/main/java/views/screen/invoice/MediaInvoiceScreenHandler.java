package views.screen.invoice;

import entity.order.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.screen.FXMLScreenHandler;
import views.screen.ViewsConfig;

import java.io.IOException;
import java.sql.SQLException;

public class MediaInvoiceScreenHandler extends FXMLScreenHandler {

  @FXML
  private HBox hboxMedia;

  @FXML
  private VBox imageLogoVbox;

  @FXML
  private ImageView image;

  @FXML
  private VBox description;

  @FXML
  private Label title;

  @FXML
  private Label numOfProd;

  @FXML
  private Label labelOutOfStock;

  @FXML
  private Label price;

  private OrderItem orderItem;

  //data coupling
  public MediaInvoiceScreenHandler(String screenPath) throws IOException {
    super(screenPath);
  }

  //stamp coupling
  public void setOrderItem(OrderItem orderItem) throws SQLException {
    this.orderItem = orderItem;
    setMediaInfo();
  }

  // clean code: setCorverImage
  void setCoverImage() {
    setImage(image, orderItem.getMedia().getImageURL());
    image.setPreserveRatio(false);
    image.setFitHeight(90);
    image.setFitWidth(83);
  }


  // clean code: tach thanh function nho
//    public void setMediaInfo() throws SQLException{
//        title.setText(orderItem.getMedia().getTitle());
//        price.setText(ViewsConfig.getCurrencyFormat(orderItem.getPrice()));
//        numOfProd.setText(String.valueOf(orderItem.getQuantity()));
//      setImage(image, orderItem.getMedia().getImageURL());
//		image.setPreserveRatio(false);
//		image.setFitHeight(90);
//		image.setFitWidth(83);
//    }
  public void setMediaInfo() throws SQLException {
    title.setText(orderItem.getMedia().getTitle());
    price.setText(ViewsConfig.getCurrencyFormat(orderItem.getPrice()));
    numOfProd.setText(String.valueOf(orderItem.getQuantity()));
    setCoverImage();
  }

}
