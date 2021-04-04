package entity.shipping;

import entity.order.Order;

public abstract class ADeliveryInfo {
    protected String name;
    protected String phone;
    protected String province;
    protected String address;
    protected String shippingInstructions;
    protected DistanceCalculatorFactory distanceCalculator;//SOLID: Vi phạm nguyên lý LSP vì sau này cần thay đổi thư viện tính khoảng cách

    public ADeliveryInfo(String name, String phone, String province, String address, String shippingInstructions, DistanceCalculatorFactory distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    /**
     * SOLID: OCP vi: khi thay doi thu vien DistanceCalculator, thì phuong thuc calculateDistance cung bi thay doi theo, do vay can viet lai phuong thuc calculateShippingFee <br>
     */
    //SOLID: vi pham nguyen li OCP vi phu thuoc truc tiep vao DistanceCalculator
    // stamp coupling
    // template method
    public abstract int calculateShippingFee(Order order);

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
