package entity.order;

import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.shipping.ADeliveryInfo;
import entity.shipping.DeliveryInfo;
import views.screen.ViewsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//  factory method: Co the co them RushOder
public class Order extends AOder {

    private int shippingFees;
    private int subtotal;
    private int tax;
    private List orderMediaList;
    protected ADeliveryInfo deliveryInfo;

    protected Order() {
        this.shippingFees = 0;
        this.subtotal = 0;
        this.tax = 0;
    }

  //stamp coupling: truyen doi tuong Cart
    protected Order(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Object object : SessionInformation.getInstance().getCartInstance().getListMedia()) {
            CartItem cartItem = (CartItem) object;
            OrderItem orderItem = new OrderItem(cartItem.getMedia(),
                    cartItem.getQuantity(),
                    cartItem.getPrice());
            orderItems.add(orderItem);
        }
        this.orderMediaList = Collections.unmodifiableList(orderItems);
        this.subtotal = cart.calSubtotal();
        this.tax = (int) (ViewsConfig.PERCENT_VAT/100) * subtotal;
    }

    public List getListOrderMedia() {
        return this.orderMediaList;
    }

    public int getShippingFees() {
        if (deliveryInfo == null) return 0;
        return this.shippingFees;
    }

    public ADeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

  //stamp coupling: truyen doi tuong DeliveryInfo
    /**
     * Communication cohesion: viec tinh shippingFees khong lien quan toi phuong thuc setDeliveryInfo, chung chi lien quan toi du lieu
     * SOLID: SRP vi chuc nang setDeliveryInfo khong nen thay doi shippingFees
     * */
    public void setDeliveryInfo(ADeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        this.shippingFees = deliveryInfo.calculateShippingFee(this);
    }

    public List getOrderMediaList() {
        return orderMediaList;
    }

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
