package entity.payment;

public class CardFactory {
    public ACardFactory getCardFactory(CardEnum type) {
        if (type == CardEnum.credit)
            return new CreditCardFactory();
        return new CreditCardFactory();

    }

}
