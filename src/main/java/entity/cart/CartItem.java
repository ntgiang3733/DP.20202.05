package entity.cart;

import entity.media.Media;

import java.sql.SQLException;

public class CartItem {

    private Media media;
    private int quantity;
    private int price;

    // cleancode: xoa phuong thuc khong su dung
//   old: public CartItem(){
//
//    }

    //stamp coupling: truyen doi tuong Media, Cart

    // cleancode: xoa phuong thuc khong su dung
    // old: public CartItem(Media media, Cart cart, int quantity, int price) {
    public CartItem(Media media, int quantity, int price) {
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

    // cleancode: bo sung phuong thuc get Total
    public int getTotal(){
        return this.getPrice() * this.getQuantity();
    }

    //cleancode:them phuong thuc availableQuantity
    public boolean availableQuantity() throws SQLException {
        return this.getMedia().getQuantity() > this.getQuantity();
    }

    @Override
    public String toString() {
        return "{"
            + " media='" + media + "'"
            + ", quantity='" + quantity + "'"
            + "}";
    }

}


