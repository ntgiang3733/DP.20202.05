package entity.cart;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * singleton: Cart can la duy nhat, do do can ap dung singleton
 */
public class Cart {

    private static Cart instance;
    private final List<CartItem> lstCartItem;

    private Cart() {
        lstCartItem = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    // stamp coupling
    public void addCartMedia(CartItem cm) {
        lstCartItem.add(cm);
    }

    // stamp coupling
    public void removeCartMedia(CartItem cm) {
        lstCartItem.remove(cm);
    }

    // clean code: dat ten ham sai y nghia
//    public List getListMedia() {
//        return lstCartItem;
//    }
    public List<CartItem> getListCartMedia() {
        return lstCartItem;
    }

    public void emptyCart() {
        lstCartItem.clear();
    }

    // clean code: su dung ten bien co nghia
    /*
    public int getTotalMedia() {
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }*/
    public int getTotalMedia() {
        int total = 0;
        for (CartItem cartItem : lstCartItem) {
            total += cartItem.getQuantity();
        }
        return total;
    }

    /**
     * Communication cohesion: phuong thuc getPrice va getQuantity co lien quan toi cm
     */
    // clean code: su dung ten bien co nghia
    /*
    public int calSubtotal() {
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
//            total += cm.getPrice()*cm.getQuantity();
            // clean code: bo sung phuong thuc getTotal()
            total += cm.getTotal();
        }
        return total;
    }*/
    public int calSubtotal() {
        int total = 0;
        for (CartItem cartItem : lstCartItem) {
            total += cartItem.getTotal();
        }
        return total;
    }

    // clean code: su dung ten bien co nghia
    // clean code: bo sung phuong thuc availableQuantity
/*    public void checkAvailabilityOfProduct() throws SQLException {
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
//            int requiredQuantity = cartItem.getQuantity();
//            int availQuantity = cartItem.getMedia().getQuantity();
//            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }*/
    public void checkAvailabilityOfProduct() throws SQLException {
        boolean allAvailable = true;
        for (CartItem cartItem : lstCartItem) {
            allAvailable = cartItem.availableQuantity();
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }

    //stamp coupling: truyen doi tuong Media
    public CartItem checkMediaInCart(Media media) {
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
        }
        return null;
    }

}
