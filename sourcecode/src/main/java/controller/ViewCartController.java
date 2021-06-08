package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart
 *
 * @author nguyenlm
 */
public class ViewCartController extends BaseController {

  /**
   * This method checks the available products in Cart
   *
   * @throws SQLException
   */
  public void checkAvailabilityOfProduct() throws SQLException {
    SessionInformation.getInstance().getCartInstance().checkAvailabilityOfProduct();//common coupling: dung bien toan cuc cartInstance
  }

  /**
   * This method calculates the cart subtotal
   *
   * @return subtotal
   */
  public int getCartSubtotal() {
    int subtotal = SessionInformation.getInstance().getCartInstance().calSubtotal(); //common coupling: dung bien toan cuc cartInstance
    return subtotal;
  }


    /**
     * This method gets the list of items in cart
     *
     * @return List[CartMedia]
     */
    //common coupling: dung bien toan cuc cartInstance
    public List getListCartMedia() {
        return SessionInformation.getInstance().getCartInstance().getListCartMedia();
    }

    protected boolean validateString(String patternString, String str) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
