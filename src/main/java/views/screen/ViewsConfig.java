package views.screen;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author
 */
public class ViewsConfig {

  // static resource
  public static final String IMAGE_PATH = "src/main/resources/assets/images";
  public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
  public static final String INVOICE_MEDIA_SCREEN_PATH = "/views/fxml/media_invoice.fxml";
  public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
  public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
  public static final String LOGIN_SCREEN_PATH = "/views/fxml/login.fxml";
  public static final String INTRO_SCREEN_PATH = "/views/fxml/intro.fxml";
  public static final String CART_SCREEN_PATH = "/views/fxml/cart.fxml";
  public static final String SHIPPING_SCREEN_PATH = "/views/fxml/shipping.fxml";
  public static final String CART_MEDIA_PATH = "/views/fxml/media_cart.fxml";
  public static final String HOME_PATH = "/views/fxml/home.fxml";
  public static final String HOME_MEDIA_PATH = "/views/fxml/media_home.fxml";
  public static final String POPUP_PATH = "/views/fxml/popup.fxml";

  // clean code: dat ten bien ko ro rang (percent_vat da duoc tinh theo ti le %, khong can chia 100)
//    public static float PERCENT_VAT = 10;
  public static float PERCENT_VAT = 0.1f;

  public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);

  //Coincidental cohesion : class này vừa chứa các hằng số vừa chứa hàm getCurrencyFormat
  public static String getCurrencyFormat(int num) {
    Locale vietnam = new Locale("vi", "VN");
    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(vietnam);
    return defaultFormat.format(num);
  }
}
