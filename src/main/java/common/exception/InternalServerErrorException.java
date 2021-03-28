package common.exception;;
//LSP : khong su dung cac phuong thuc cua AimsException
public class InternalServerErrorException extends PaymentException {

	public InternalServerErrorException() {
		super("ERROR: Internal Server Error!");
	}

}
