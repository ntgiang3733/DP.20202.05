package entity.shipping;

public class DeliveryInfo extends ADeliveryInfo {
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorFactory distanceCalculator) {
        super(name, phone, province, address, shippingInstructions, distanceCalculator);
    }
}
