package subsystem.interbank;

import common.exception.*;
import entity.payment.*;
import utils.MyMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author
 * singleton: day la mot class chi co cac method ho tro, nen dung singleton de toi uu bo nho
 */
public class InterbankPayloadConverter {

    private static InterbankPayloadConverter instance;

    public static InterbankPayloadConverter getInstance(){
        if(instance == null){
            instance = new InterbankPayloadConverter();
        }
        return instance;
    }

    private InterbankPayloadConverter(){}

    /**
     * <h3><i>coincidental cohesion: phuong thuc getToday() khong lien quan toi class nay</i></h3>
     * Convert from native entity into interbank required format
     * @param card
     * @param amount
     * @param contents
     * @return {@link Map}
     */
	 // stamp coupling: truyen doi tuong CreditCard
    String convertToRequestPayload(ACard card, int amount, String contents) {
        Map<String, Object> transaction = new MyMap();

        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new InvalidCardException();
        }
        transaction.put("command", InterbankConfigs.PAY_COMMAND);
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", InterbankConfigs.VERSION);
        requestMap.put("transaction", transaction);

        return ((MyMap) requestMap).toJSON();
    }

    /**
     * coupling: data
     * Read the response from interbank server
     * @param responseText
     * @return
     */
    //SOLID: vi pham nguyen li OCP vi phu thuoc vao responseText, neu thay doi se phai sua trong code
    PaymentTransaction extractPaymentTransaction(String responseText) {
        MyMap response = convertJSONResponse(responseText);

        if (response == null)
            return null;
        MyMap transaction = (MyMap) response.get("transaction");
        ACard card = CardFactory.createCreditCard(
                (String) transaction.get("cardCode"),
                (String) transaction.get("owner"),
                (String) transaction.get("dateExpired"),
                Integer.parseInt((String) transaction.get("cvvCode")));

        PaymentTransaction trans = new PaymentTransaction(
                (String) response.get("errorCode"),
                card,
                (String) transaction.get("transactionId"),
                (String) transaction.get("transactionContent"),
                Integer.parseInt((String) transaction.get("amount")),
                (String) transaction.get("createdAt"));

        switch (trans.getErrorCode()) {
            case "00":
                break;
            case "01":
                throw new InvalidCardException();
            case "02":
                throw new NotEnoughBalanceException();
            case "03":
                throw new InternalServerErrorException();
            case "04":
                throw new SuspiciousTransactionException();
            case "05":
                throw new NotEnoughTransactionInfoException();
            case "06":
                throw new InvalidVersionException();
            case "07":
                throw new InvalidTransactionAmountException();
            default:
                throw new UnrecognizedException();
        }

        return trans;
    }

    /**
     * Convert response from interbank server as JSON-formatted String into a proper Map
     * @param responseText
     * @return
     */
    // coupling: data ->
    private MyMap convertJSONResponse(String responseText) {
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new UnrecognizedException();
        }
        return response;
    }

    /**
     * <h3><i>Coincidental cohesion: phuong thuc getToday() khong lien quan toi class nay</i></h3>
     * Return a {@link String String} that represents the current time in the format of yyyy-MM-dd HH:mm:ss.
     * @author hieudm
     * @return the current time as {@link String String}.
     *
     * <br> SOLID: SRP chuc nang getToday khong lien quan toi class nay
     */
    private String getToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
