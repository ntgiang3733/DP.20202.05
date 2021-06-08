package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.CalculatorShippingFee;
import entity.shipping.DeliveryInfo;
import entity.shipping.DistanceAdapter;
import utils.Utils;
import views.screen.shipping.DeliveryInfoObj;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class controls the flow of place order usecase in our AIMS project
 *
 * @author nguyenlm
 */
//SOLID: Vi phạm nguyên lý SRC vì class vừa thực hiện các nhiệm vụ liên quan đến order,
// vừa thực hiện các nhiệm vụ về validate
// singleton
public class PlaceOrderController extends BaseController {

  private static PlaceOrderController instance;

  public static PlaceOrderController getInstance() {
    if (instance == null) {
      instance = new PlaceOrderController();
    }
    return instance;
  }

  private PlaceOrderController() {
  }

  /**
   * Just for logging purpose
   */
  private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

  /**
   * This method checks the availability of product when user click PlaceOrder button
   *
   * @throws SQLException
   */
  //common coupling: dung bien toan cuc cartInstance
  public void placeOrder() throws SQLException {
    SessionInformation.getInstance().getCartInstance().checkAvailabilityOfProduct();
  }

  /**
   * This method creates the new Order based on the Cart
   *
   * @return Order
   * @throws SQLException
   */
  //common coupling: dung bien toan cuc cartInstance
  public Order createOrder() throws SQLException {
    return new Order(SessionInformation.getInstance().getCartInstance());
  }

  /**
   * This method creates the new Invoice based on order
   *
   * @param order
   * @return Invoice
   */
  // stamp coupling
  public Invoice createInvoice(Order order) {
    return new Invoice(order);
  }

  /**
   * This method takes responsibility for processing the shipping info from user
   *
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
  // stamp coupling
//    // clean code: su dung DeliveryInfoObj de truyen du lieu
//  public DeliveryInfo processDeliveryInfo(HashMap info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
//      LOGGER.info("Process Delivery Info");
//      LOGGER.info(info.toString());
//      validateDeliveryInfo(info);
//      DeliveryInfo deliveryInfo = new DeliveryInfo(
//          String.valueOf(info.get("name")),
//          String.valueOf(info.get("phone")),
//          String.valueOf(info.get("province")),
//          String.valueOf(info.get("address")),
//          String.valueOf(info.get("instructions")),
//          new DistanceCalculator());
//      System.out.println(deliveryInfo.getProvince());
//      return deliveryInfo;
//  }
  public DeliveryInfo processDeliveryInfo(DeliveryInfoObj info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
    LOGGER.info("Process Delivery Info");
    LOGGER.info(info.toString());
    validateDeliveryInfo(info);
    DeliveryInfo deliveryInfo = new DeliveryInfo(
        info,
        new DistanceAdapter());
      //  design pattern: strategy
      deliveryInfo.setCalShip(new CalculatorShippingFee());
    System.out.println(deliveryInfo.getProvince());
    return deliveryInfo;
  }

  /**
   *  Coincidental cohesion:  phuong thuc validate nen dat o lop khac
   *  logical cohesion: cac method validate khac nhau cung xuat hien trong class
   */
  /**
   * The method validates the info
   *
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
  // stamp coupling
  //Coincidental Cohesion: các hàm validate và các hàm phía trên nên tách riêng
  //SOLID: vi pham nguyen li OCP vi phu thuoc vao info
  // clean code: su dung DeliveryInfoObj de truyen du lieu
//    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
//        if (validatePhoneNumber(info.get("phone"))
//                || validateName(info.get("name"))
//                || validateAddress(info.get("address"))) return;
//        else throw new InvalidDeliveryInfoException();
//    }
  public void validateDeliveryInfo(DeliveryInfoObj info) throws InterruptedException, IOException, InvalidDeliveryInfoException {
    if (validatePhoneNumber(info.getPhone())
        || validateName(info.getName())
        || validateAddress(info.getAddress())) return;
    else throw new InvalidDeliveryInfoException();
  }

  // data coupling
  public boolean validatePhoneNumber(String phoneNumber) {
    // clean code: ko su dung hang so
    // if (phoneNumber.length() != 10) return false;
    if (phoneNumber.length() != Utils.PHONE_NUMBER_LENGTH) return false;
    if (!phoneNumber.startsWith("0")) return false;
    try {
      Integer.parseInt(phoneNumber);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  // data coupling
  public boolean validateName(String name) {
    if (Objects.isNull(name)) return false;
    String patternString = "^[a-zA-Z\\s]*$";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(name);
    return matcher.matches();
  }

  // data coupling
  //logical cohesion: validate function in name and address is similar but written in 2
  //different function -> abstract class validate
  // coincidental cohesion: validate nen dat trong lop khac
  public boolean validateAddress(String address) {
    if (Objects.isNull(address)) return false;
    String patternString = "^[a-zA-Z\\s]*$";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(address);
    return matcher.matches();
  }
}
