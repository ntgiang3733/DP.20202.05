package entity.shipping;

private static final double MULTIPLIER = 1.2;

public class ShippingFeeStrategy implements CalShippingFreeStrategy{

    @Override
    public int calShippingFee(int  distance) {
        return (int) (distance * MULTIPLIER);
    }
}