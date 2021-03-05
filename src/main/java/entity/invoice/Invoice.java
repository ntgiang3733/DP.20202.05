package entity.invoice;

import entity.order.Order;

public class Invoice {

    private Order order;
    private int amount;
    
    public Invoice(){

    }
    // stamp coupling
    public Invoice(Order order){
        this.order = order;
        this.amount = order.getTotal();
    }

    public Order getOrder() {
        return order;
    }
    // data coupling
    public void setAmount(int amount) {
        this.amount = amount;
    } //data

    public int getAmount() {
        return amount;
    }

    public void saveInvoice(){
        
    }
}
