package entity.shipping;

import org.example.DistanceCalculator;

public class DistanceAdapter implements IDistanceAdapter{
    DistanceCalculator adaptee;

    public DistanceAdapter(){
        adaptee = new DistanceCalculator();
    }

    public int calculateDistance(String address, String province) {
        return adaptee.calculateDistance(address, province);
    }
}
