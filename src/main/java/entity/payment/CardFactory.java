package entity.payment;

// design pattern: factory
public class CardFactory {
    public static CreditCard createCreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        return new CreditCard(cardCode, owner, dateExpired, cvvCode);
    }

    private CardFactory() {
    }
}
