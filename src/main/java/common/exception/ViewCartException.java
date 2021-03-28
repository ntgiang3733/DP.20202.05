package common.exception;;

/**
 * The ViewCartException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 *///LSP : khong su dung cac phuong thuc cua AimsException
public class ViewCartException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public ViewCartException() {

	}

	// coupling: data -> chi phu thuoc 1 tham so
	public ViewCartException(String message) {
		super(message);
	}//data

}
