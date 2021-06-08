package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.ACard;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 *
 * @author hieud
 *
 */
// design pattern: singleton
public class InterbankSubsystem implements InterbankInterface {


    private static InterbankSubsystem instance;
    /**
     * Represent the controller of the subsystem
     */
    private final InterbankSubsystemController ctrl;

    /**
     * Initializes a newly created {@code InterbankSubsystem} object so that it
     * represents an Interbank subsystem.
     */
    private InterbankSubsystem() {
        this.ctrl = new InterbankSubsystemController();
    }

    public static InterbankSubsystem getInstance() {
        if (instance == null) {
            instance = new InterbankSubsystem();
        }
        return instance;
    }

    /**
     * @see InterbankInterface#payOrder(entity.payment.ACard, int,
     * String)
     */
    public PaymentTransaction payOrder(ACard card, int amount, String contents) {
        return ctrl.payOrder(card, amount, contents);
    }

    /**
     * @see InterbankInterface#refund(CreditCard, int,
     *      String)
     */
    //stamp coupling
    @Override
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
        return ctrl.refund(card, amount, contents);
	}
}
