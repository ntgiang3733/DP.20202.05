package controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return SessionInformation.getInstance().getCartInstance().getListCartMedia();//common coupling: dung bien toan cuc cartInstance
    }

    protected boolean validateString(String patternString, String str){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
