package entity.cart;

import entity.media.Media;

public class CartItem {
    
    private Media media;
    private int quantity;
    private int price;

    public CartItem(){

    }

    //stamp coupling: truyen doi tuong Media, Cart
    public CartItem(Media media, Cart cart, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    public Media getMedia() {
        return this.media;
    }
    
    //stamp coupling: truyen doi tuong Media
    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    // coupling: data -> chỉ phụ thuộc vào một số tham số
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" 
            + " media='" + media + "'" 
            + ", quantity='" + quantity + "'" 
            + "}";
    }

}

    
