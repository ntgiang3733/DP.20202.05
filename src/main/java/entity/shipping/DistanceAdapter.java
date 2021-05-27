package entity.shipping;

import org.example.DistanceCalculator;

public class DistanceAdapter {
    DistanceCalculator distanceAdaptee;

    public DistanceAdapter(){
        this.distanceAdaptee = new DistanceCalculator();
    }
    int calculateDistance(String address, String province) {
        return distanceAdaptee.calculateDistance(address, province);
    }
}
