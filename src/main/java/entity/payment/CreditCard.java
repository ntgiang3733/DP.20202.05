package entity.payment;

/**
 * @author
 */
public class CreditCard extends ACard {

    protected int cvvCode;
    private final String cardCode;
    private final String owner;
    private final String dateExpired;

    // coupling: data -> chỉ phụ thuộc vào 4 tham số
    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}
