package entity.shipping;

import entity.order.Order;

// dung cho cach tinh khoang cach moi
public class NewDeliveryInfo extends ADeliveryInfo {
    public NewDeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorFactory distanceCalculator) {
        super(name, phone, province, address, shippingInstructions, distanceCalculator);
    }

    public int calculateShippingFee(Order order) {
        return 1;
    }
}
