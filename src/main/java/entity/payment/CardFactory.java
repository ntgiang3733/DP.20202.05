package entity.payment;

public class CardFactory {
    public static ACardFactory getCardFactory(CardEnum type) {
        if (type == CardEnum.credit)
            return new CreditCardFactory();
        return new CreditCardFactory();

    }

    public static CreditCard createCreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        return new CreditCard(cardCode, owner, dateExpired, cvvCode);
    }

    private CardFactory() {
    }
}
