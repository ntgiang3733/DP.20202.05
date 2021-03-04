package common.exception;;

public class PaymentException extends RuntimeException {

	// coupling: data -> chỉ phụ thuộc vào 1 tham số
	public PaymentException(String message) {
		super(message);
	}
}
