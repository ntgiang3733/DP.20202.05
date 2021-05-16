package entity.shipping;

import entity.order.Order;

public class CalculatorShippingFee implements CalculatorShippingStrategy {
    @Override
    public int calculateShippingFee(int distance, Order order) {
        return (int) (distance * ShippingConfigs.DISTANCE_CALCULATOR_FEE);
    }
}
