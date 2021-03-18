package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;

public class DeliveryInfo {

    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    /**
     * <h3>SOLID: OCP vi: <i>khi thay doi thu vien DistanceCalculator, thì phuong thuc calculateDistance cung bi thay doi theo, do vay can viet lai phuong thuc calculateShippingFee</i> </h3>
     * */
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * 1.2);
    }//data

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}
