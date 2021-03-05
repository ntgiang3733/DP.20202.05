package entity.order;

import entity.media.Media;

public class OrderItem {
    
    private Media media;
    private int price;
    private int quantity;

    // stamp coupling: truyen doi tuong Media
    public OrderItem(Media media, int quantity, int price) {
        this.media = media;
        this.quantity = quantity;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "{" +
            "  media='" + media + "'" +
            ", quantity='" + quantity + "'" +
            ", price='" + price + "'" +
            "}";
    }
    
    public Media getMedia() {
        return this.media;
    }

    // stamp coupling: truyen doi tuong Media
    public void setMedia(Media media) {
        this.media = media;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    // coupling: data -> chi phu thuoc mot so tham so
    public void setPrice(int price) {
        this.price = price;
    }

}
