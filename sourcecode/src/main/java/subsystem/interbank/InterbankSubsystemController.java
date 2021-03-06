package subsystem.interbank;

import entity.payment.ACard;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class InterbankSubsystemController {

	private static InterbankPayloadConverter interbankPayloadConverter = InterbankPayloadConverter.getInstance();
	private static InterbankBoundary interbankBoundary = InterbankBoundary.getInstance();

	 // stamp coupling: truyen doi tuong CreditCard
	public PaymentTransaction refund(ACard card, int amount, String contents) {
		return null;
	}

	 // stamp coupling: truyen doi tuong CreditCard
	public PaymentTransaction payOrder(ACard card, int amount, String contents) {
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}//stamp coupling

}
