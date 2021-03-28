package common.exception;;

/**
 * The ProcessInvoiceException wraps all unchecked exceptions You can use this
 * exception to inform 
 * 
 * @author nguyenlm
 */
//LSP : khong su dung cac phuong thuc cua AimsException
public class ProcessInvoiceException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public ProcessInvoiceException() {

	}

	// data coupling
	public ProcessInvoiceException(String message) {
		super(message);
	}//data

}
