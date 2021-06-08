package entity.shipping;

import entity.order.Order;

public interface CalculatorShippingStrategy {
    int calculateShippingFee(int distance, Order order);
}
