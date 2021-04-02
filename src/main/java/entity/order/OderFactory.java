package entity.order;

import entity.cart.Cart;

public class OderFactory {
    private OderFactory(){}
    public static Order getOder(OderEnum type, Cart cart){
        // kiem tra loai dat hang, loai hang
        return new Order(cart);
    }
}
