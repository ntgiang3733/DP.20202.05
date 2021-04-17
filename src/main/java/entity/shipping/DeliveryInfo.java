package entity.shipping;

import entity.order.Order;

public class DeliveryInfo extends ADeliveryInfo {
    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorFactory distanceCalculator) {
        super(name, phone, province, address, shippingInstructions, distanceCalculator);
    }

    // cleancode: ko su dung magic_number
/*
    @Override
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * 1.2);
    }*/
    @Override
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * ShippingConfigs.DISTANCE_CALCULATOR_FEE);
    }
}
