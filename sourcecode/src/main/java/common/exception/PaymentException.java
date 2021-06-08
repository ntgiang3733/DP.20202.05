package common.exception;;

public class PaymentException extends RuntimeException {

	// coupling: data -> chi phu thuoc 1 tham so
	public PaymentException(String message) {
		super(message);
	}
}
