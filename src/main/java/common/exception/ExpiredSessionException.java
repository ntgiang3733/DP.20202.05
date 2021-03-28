package common.exception;

/**
 * @author
 */
//LSP : khong su dung cac phuong thuc cua AimsException
public class ExpiredSessionException extends AimsException {

    public ExpiredSessionException() {
        super("ERROR: Your session has expired. Please login again!");
    }
}
