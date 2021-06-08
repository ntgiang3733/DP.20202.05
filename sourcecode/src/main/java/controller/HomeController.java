package controller;

import java.sql.SQLException;
import java.util.List;

import dao.media.MediaDaoEnum;
import dao.media.MediaDAOFactory;
import entity.cart.CartItem;
import entity.media.Media;

/**
 * This class controls the flow of events in homescreen
 *
 * @author nguyenlm
 */
public class HomeController extends BaseController {


  /**
   * this method gets all Media in DB and return back to home to display
   *
   * @return List[Media]
   * @throws SQLException
   */
  public static List getAllMedia() throws SQLException {
    return MediaDAOFactory.getMediaDao(MediaDaoEnum.media).getAllMedia();
  }

    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     *
     * @param media {@link Media Media}
     * @return CartMedia or null
     */
    // stamp coupling
    //common coupling: dung bien toan cuc cartInstance
    public CartItem checkMediaInCart(Media media) {
        return SessionInformation.getInstance().getCartInstance().checkMediaInCart(media);
    }
}
