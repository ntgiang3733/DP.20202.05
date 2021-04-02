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

public abstract class AOder {

    public abstract List getListOrderMedia();

    public abstract int getShippingFees();

    public abstract ADeliveryInfo getDeliveryInfo();

    public abstract void setDeliveryInfo(ADeliveryInfo deliveryInfo) ;

    public abstract List getOrderMediaList();

    public abstract int getSubtotal();

    public abstract int getTax();

    public abstract int getTotal();
}
