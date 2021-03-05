package common.exception;

public class InternalServerException extends PaymentException {
    // data coupling
    public InternalServerException(String message) {
        super(message);
    }
}
