package entity.payment;

public class PaymentTransaction {
    private final String errorCode;
    private final ACard card;
    private final String transactionId;
    private final String transactionContent;
    private final int amount;
    private final String createdAt;

    // stamp coupling: truyen doi tuong CreditCard

    /**
     * SOLID: vi pham OCP vi PaymentTransaction dang phu thuoc truc tiep vaof doi tuong CreditCard, sau ny khi co card moi thi phai sua lai code <br>
     */
    public PaymentTransaction(String errorCode, ACard card, String transactionId, String transactionContent, int amount, String createdAt) {
        super();
        this.errorCode = errorCode;
        this.card = card;
        this.transactionId = transactionId;
        this.transactionContent = transactionContent;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
