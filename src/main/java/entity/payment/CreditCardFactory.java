package entity.payment;

public class CreditCardFactory extends ACardFactory{
    public ACard getCreditCard(String params1, String params2, String params3, int params4){
        return new CreditCard(params1, params2, params3, params4);
    }

    protected CreditCardFactory(){}
}
