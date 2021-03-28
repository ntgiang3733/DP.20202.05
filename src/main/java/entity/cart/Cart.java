package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;
//Singleton : can singleton cho cart de chac chan chi co duy nhat 1 cart cho nguoi dung
public class Cart {
    
    private List<CartItem> lstCartItem;
    private static Cart instance;

    public static Cart getInstance(){
        if(instance == null){
            instance = new Cart();
        }
        return instance;
    }
    private Cart() {
        lstCartItem = new ArrayList<>();
    }
    // stamp coupling
    public void addCartMedia(CartItem cm){
        lstCartItem.add(cm);
    }

    // stamp coupling
    public void removeCartMedia(CartItem cm){
        lstCartItem.remove(cm);
    }

    public List getListMedia(){
        return lstCartItem;
    }

    public void emptyCart(){
        lstCartItem.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    /**
     * Communication cohesion: phuong thuc getPrice va getQuantity co lien quan toi cm
     * */
    public int calSubtotal(){
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getPrice()*cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvailable = false;
        }
        if (!allAvailable) throw new MediaNotAvailableException("Some media not available");
    }
    
    //stamp coupling: truyen doi tuong Media
    public CartItem checkMediaInCart(Media media){
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == media.getId()) return cartItem;
        }
        return null;
    }

}
