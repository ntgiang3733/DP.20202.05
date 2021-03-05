package common.exception;;

/**
 * The MediaNotAvailableException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class MediaNotAvailableException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public MediaNotAvailableException() {

	}

	// coupling: data -> chi phu thuoc 1 tham so
	public MediaNotAvailableException(String message) {
		super(message);
	}

}