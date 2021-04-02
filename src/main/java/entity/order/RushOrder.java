package entity.order;

import entity.shipping.ADeliveryInfo;
import entity.shipping.DeliveryInfo;

import java.util.List;

public class RushOrder extends AOder{
    @Override
    public List getListOrderMedia() {
        return null;
    }

    @Override
    public int getShippingFees() {
        return 0;
    }

    @Override
    public DeliveryInfo getDeliveryInfo() {
        return null;
    }

    @Override
    public void setDeliveryInfo(ADeliveryInfo deliveryInfo) {

    }

    @Override
    public List getOrderMediaList() {
        return null;
    }

    @Override
    public int getSubtotal() {
        return 0;
    }

    @Override
    public int getTax() {
        return 0;
    }

    @Override
    public int getTotal() {
        return 0;
    }
}
