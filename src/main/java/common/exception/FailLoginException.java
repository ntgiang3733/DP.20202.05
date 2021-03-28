package common.exception;

/**
 * @author
 */
//LSP : khong su dung cac phuong thuc cua AimsException
public class FailLoginException extends AimsException {

    public FailLoginException() {
        super("ERROR: Fail to Login. Please try again!");
    }
}
