package entity.shipping;

import entity.order.Order;
import org.example.DistanceCalculator;
import views.screen.shipping.DeliveryInfoObj;

public class DeliveryInfo {
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculator distanceCalculator;//SOLID: Vi phạm nguyên lý LSP vì sau này cần thay đổi thư viện tính khoảng cách

    protected CalculatorShippingStrategy behaviorCalShip;

    // clean code: su dung DeliveryInfo de truyen du lieu
//    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorFactory distanceCalculator) {
//        super(name, phone, province, address, shippingInstructions, distanceCalculator);
//  }
//    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorFactory distanceCalculator) {
//        this.name = name;
//        this.phone = phone;
//        this.province = province;
//        this.address = address;
//        this.shippingInstructions = shippingInstructions;
//        this.distanceCalculator = distanceCalculator;
//    }
    public DeliveryInfo(DeliveryInfoObj infoObj, DistanceCalculator distanceCalculator) {
        this.name = infoObj.getName();
        this.phone = infoObj.getPhone();
        this.province = infoObj.getProvince();
        this.address = infoObj.getAddress();
        this.shippingInstructions = infoObj.getInstructions();
        this.distanceCalculator = distanceCalculator;
    }

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

    public void setCalShip(CalculatorShippingStrategy behaviorCalShip) {
        this.behaviorCalShip = behaviorCalShip;
    }

    // clean code: ko su dung magic_number

    /**
     * SOLID: OCP vi: khi thay doi thu vien DistanceCalculator, thì phuong thuc calculateDistance cung bi thay doi theo, do vay can viet lai phuong thuc calculateShippingFee <br>
     */
    //SOLID: vi pham nguyen li OCP vi phu thuoc truc tiep vao DistanceCalculator
    // stamp coupling
/*
    @Override
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return (int) (distance * 1.2);
    }*/
    // design pattern: su dung strategy cho phuong thuc calculateShippingFee
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
//        return (int) (distance * ShippingConfigs.DISTANCE_CALCULATOR_FEE);
        if (this.behaviorCalShip == null)
            this.behaviorCalShip = new CalculatorShippingFee();
        return this.behaviorCalShip.calculateShippingFee(distance, order);
    }
}
