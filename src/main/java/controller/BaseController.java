package controller;

import java.util.List;

import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */
public class BaseController {
    
    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
    // stamp coupling
    public CartItem checkMediaInCart(Media media){
        return SessionInformation.getInstance().getCartInstance().checkMediaInCart(media); //common coupling: dung bien toan cuc cartInstance
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public List getListCartMedia(){
        return SessionInformation.getInstance().getCartInstance().getListMedia();//common coupling: dung bien toan cuc cartInstance
    }
}
