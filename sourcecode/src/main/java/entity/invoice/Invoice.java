package entity.invoice;

import entity.order.Order;

import java.util.List;

public class Invoice {

    private final Order order;
    private int amount;

    // clean code: bo phuong thuc ko su dung
//    public Invoice(){
//
//    }

    // stamp coupling
    public Invoice(Order order) {
        this.order = order;
        this.amount = order.getTotal();
    }

    public Order getOrder() {
        return order;
    }

    public int getAmount() {
        return amount;
    }

    // data coupling
    public void setAmount(int amount) {
        this.amount = amount;
    } //data

    // coincidental cohesion: saveInvoice() khong lien quan toi nghiep vu cua class
    public void saveInvoice() {
    }

    public List getListOrderMedia() {
        return this.order.getListOrderMedia();
    }

}
