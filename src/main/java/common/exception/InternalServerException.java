package common.exception;
//LSP : khong su dung cac phuong thuc cua AimsException
public class InternalServerException extends PaymentException {
    // data coupling
    public InternalServerException(String message) {
        super(message);
    }
}
