package entity.order;

import entity.media.Media;

public class OrderItem {
    
    private Media media;
    private int price;
    private int quantity;

    // coupling: data -> chỉ phụ thuộc vào một số tham số
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

}
