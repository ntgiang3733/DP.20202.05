package controller;

import java.sql.SQLException;

import entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart
 * @author nguyenlm
 */
public class ViewCartController extends BaseController{
    
    /**
     * This method checks the available products in Cart
     * @throws SQLException
     */
    public void checkAvailabilityOfProduct() throws SQLException{
        SessionInformation.getInstance().getCartInstance().checkAvailabilityOfProduct();//common coupling: dung bien toan cuc cartInstance
    }

    /**
     * This method calculates the cart subtotal
     * @return subtotal
     */
    public int getCartSubtotal(){
        int subtotal = SessionInformation.getInstance().getCartInstance().calSubtotal(); //common coupling: dung bien toan cuc cartInstance
        return subtotal;
    }

}
