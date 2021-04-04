package entity.payment;

// factory method
public abstract class ACardFactory {
    public ACard getCreditCard(String params1, String params2, String params3, int params4){
        return new CreditCard(params1, params2, params3, params4);
    };

    protected ACardFactory(){}
//    public ACard getDepositCard();
}
