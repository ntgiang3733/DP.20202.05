package entity.order;

import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.order.state.OrderCreated;
import entity.shipping.DeliveryInfo;
import views.screen.ViewsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    protected DeliveryInfo deliveryInfo;
    private int shippingFees;
    private final int subtotal;
    private final int tax;
    private List orderMediaList;

    // design pattern: state
    private OrderState state;

    protected Order() {
        this.shippingFees = 0;
        this.subtotal = 0;
        this.tax = 0;

        this.state = new OrderCreated();
    }

    //stamp coupling: truyen doi tuong Cart
    public Order(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object object : SessionInformation.getInstance().getCartInstance().getListCartMedia()) {
            CartItem cartItem = (CartItem) object;
            OrderItem orderItem = new OrderItem(cartItem.getMedia(),
                cartItem.getQuantity(),
                cartItem.getPrice());
            orderItems.add(orderItem);
        }
        this.orderMediaList = Collections.unmodifiableList(orderItems);
        this.subtotal = cart.calSubtotal();
        // clean code: dat ten bien ko ro rang (percent_vat da duoc tinh theo ti le %, khong can chia 100)
//        old: this.tax = (int) (ViewsConfig.PERCENT_VAT/100) * subtotal;
        this.tax = (int) (ViewsConfig.PERCENT_VAT) * subtotal;

        this.state = new OrderCreated();
    }

    public void setState(OrderState state){
        this.state = state;
    }

    public List getListOrderMedia() {
        return this.orderMediaList;
    }

    public int getShippingFees() {
        if (deliveryInfo == null) return 0;
        return this.shippingFees;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    //stamp coupling: truyen doi tuong DeliveryInfo

    /**
     * Communication cohesion: viec tinh shippingFees khong lien quan toi phuong thuc setDeliveryInfo, chung chi lien quan toi du lieu
     * SOLID: SRP vi chuc nang setDeliveryInfo khong nen thay doi shippingFees
     */
    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        this.shippingFees = deliveryInfo.calculateShippingFee(this);
    }

    // clean code: bo phuong thuc ko su dung
//    public List getOrderMediaList() {
//        return orderMediaList;
//    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTax() {
        return tax;
    }

    public int getTotal() {
        return this.subtotal + this.tax + this.shippingFees;
    }
}
