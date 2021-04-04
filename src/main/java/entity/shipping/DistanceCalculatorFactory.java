package entity.shipping;

import org.example.DistanceCalculator;

// factory method: Distance co the thay doi cach tinh sau nay
public class DistanceCalculatorFactory {

    public int calculateDistance(String address, String province) {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        return distanceCalculator.calculateDistance(address, province);
    }
}
