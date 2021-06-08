package views.screen.shipping;

public class DeliveryInfoObj {
  private final String name;
  private final String phone;
  private final String address;
  private final String instructions;
  private final String province;

  public DeliveryInfoObj(String name, String phone, String address, String instructions, String province) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.instructions = instructions;
    this.province = province;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getAddress() {
    return address;
  }

  public String getInstructions() {
    return instructions;
  }

  public String getProvince() {
    return province;
  }
}
