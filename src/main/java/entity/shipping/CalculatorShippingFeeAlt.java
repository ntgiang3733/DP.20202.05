package entity.shipping;

import entity.order.Order;

public class CalculatorShippingFeeAlt implements CalculatorShippingStrategy{
    @Override
    public int calculateShippingFee(int distance, Order order) {
        return 0;
    }
}
