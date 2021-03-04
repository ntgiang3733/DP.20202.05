package common.exception;

/**
 * The PlaceOrderException wraps all unchecked exceptions You can use this
 * exception to inform 
 * 
 * @author nguyenlm
 */
public class PlaceOrderException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public PlaceOrderException() {

	}

	// coupling: data -> chỉ phụ thuộc vào 1 tham số
	public PlaceOrderException(String message) {
		super(message);
	}

}
