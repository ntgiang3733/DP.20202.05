package controller;

import java.util.*;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.*;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import utils.DateTimeUtils;
import views.screen.ResponseMessage;


/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 *
 * @author hieud
 */
public class PaymentController extends BaseController {

    /**
     * Represent the card used for payment
     */
    private ACard card;

    /**
     * Represent the Interbank subsystem
     */
    private InterbankInterface interbank;

    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link String String} representing the date in the
     * required format "mmyy" .
     *
     * @param date - the {@link String String} represents the input date
     * @return {@link String String} - date representation of the required
     * format
     * @throws InvalidCardException - if the string does not represent a valid date
     *                              in the expected format
     */
    // coupling: data -> chi phu thuoc 1 tham so
    // cleancode: dua cac cau dieu kien vao 1 function
    /*
    private String getExpirationDate(String date) throws InvalidCardException {
        String[] strs = date.split("/");
        if (strs.length != 2) {
            throw new InvalidCardException();
        }

        String expirationDate = null;
        int month = -1;
        int year = -1;

        try {
            month = Integer.parseInt(strs[0]);
            year = Integer.parseInt(strs[1]);
            if (month < 1 || month > 12 || year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100) {
                throw new InvalidCardException();
            }
            expirationDate = strs[0] + strs[1];

        } catch (Exception ex) {
            throw new InvalidCardException();
        }

        return expirationDate;
    }
*/
    private String getExpirationDate(String date) throws InvalidCardException {
        try {
            String[] strs = DateTimeUtils.getMonthYearFromString(date);
            return DateTimeUtils.getExpiredDate(strs[0], strs[1]);
        } catch (Exception exc) {
            throw new InvalidCardException();
        }
    }

    /**
     * Pay order, and then return the result with a message.
     *
     * @param amount         - the amount to pay
     * @param contents       - the transaction contents
     * @param cardNumber     - the card number
     * @param cardHolderName - the card holder name
     * @param expirationDate - the expiration date in the format "mm/yy"
     * @param securityCode   - the cvv/cvc code of the credit card
     * @return {@link Map Map} represent the payment result with a
     * message.
     * <br>
     * SOLID: DIP vi interbank phu thuoc truc tiep vao lop InterbankSubsystem ma khong phu thuoc vao interface
     */
    // coupling: data -> chi phu thuoc mot so tham so
    //SOLID: vi pham nguyen ly OCP vi them phuong thuc thanh toan phai sua payOrder
   /*
      // cleancode: chuyen cac cau lenh thanh 1 function
      // cleancode: return ngay khi co the
    public Map<String, String> payOrder(int amount, String contents, String cardNumber, String cardHolderName,
                                        String expirationDate, String securityCode) {
        Map<String, String> result = new Hashtable<String, String>();
        result.put("RESULT", "PAYMENT FAILED!");
        try {
            this.card = (CreditCard) CardFactory.createCreditCard(
                    cardNumber,
                    cardHolderName,
                    getExpirationDate(expirationDate),
                    Integer.parseInt(securityCode));

            this.interbank = new InterbankSubsystem();
            PaymentTransaction transaction = interbank.payOrder(card, amount, contents);

            result.put("RESULT", "PAYMENT SUCCESSFUL!");
            result.put("MESSAGE", "You have successfully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            result.put("MESSAGE", ex.getMessage());
        }
        return result;
    }
*/
    public ResponseMessage payOrder(int amount, String contents, String cardNumber, String cardHolderName,
                                        String expirationDate, String securityCode) {
        try {
            this.card = (CreditCard) CardFactory.createCreditCard(
                    cardNumber,
                    cardHolderName,
                    getExpirationDate(expirationDate),
                    Integer.parseInt(securityCode));
            this.interbank = InterbankSubsystem.getInstance();
            PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
            return new ResponseMessage("PAYMENT SUCCESSFUL!", "You have successfully paid the order!");
        } catch (PaymentException | UnrecognizedException ex) {
            return new ResponseMessage( "PAYMENT FAILED!",  ex.getMessage());
        }
    }

    // cleancode: interface: hide delegate: chuyen phuong thuc empty card vao SessionInformation
//    /**
//     * coincidental cohesion: phuong thuc empty card khong lien quan toi lop PaymentController
//     */
//    public void emptyCart() {
//        SessionInformation.getInstance().getCartInstance().emptyCart();//common coupling: dung bien toan cuc cartInstance
//    }
    public void emptyCart() {
        SessionInformation.getInstance().emptyCart();
    }
}