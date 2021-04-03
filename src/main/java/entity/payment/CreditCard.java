package entity.payment;

/**
 * @author
 */
public class CreditCard extends ACard{

    private String cardCode;
    private String owner;
    private String dateExpired;
    protected int cvvCode;

    // coupling: data -> chỉ phụ thuộc vào 4 tham số
    public CreditCard(String cardCode, String owner, String dateExpired, int cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }
}
