package common.exception;;

/**
 * The MediaUpdateException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class MediaUpdateException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public MediaUpdateException() {

	}

	// coupling: data -> chỉ phụ thuộc vào 1 tham số
	public MediaUpdateException(String message) {
		super(message);
	}//data

}
