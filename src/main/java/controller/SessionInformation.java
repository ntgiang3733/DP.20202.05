package controller;

import entity.cart.Cart;
import entity.user.User;

import java.time.LocalDateTime;

/**
 * @author
 * singleton: can phuong thuc private constructor de dam bao SessionInformation chi truy cap cac bien static
 */
//Singleton: SessionInformation chi nen duoc tao mot the hien trong qua trinh chay, vi no khong thay doi trong mot phien lam viec
public class SessionInformation {

    public static final int TIME_SESSION_EXPIRED_HOUR = 24;
    private User mainUser; //
    private Cart cartInstance;
    private LocalDateTime expiredTime;

    private static SessionInformation instance;

    public static SessionInformation getInstance(){
        if(instance == null){
            instance = new SessionInformation();
        }
        return instance;
    }

    private SessionInformation(){
        cartInstance = Cart.getInstance();
    }

    public User getMainUser() {
        return mainUser;
    }

    public boolean hasMainUser(){
        return this.mainUser != null;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    public Cart getCartInstance() {
        return cartInstance;
    }

    public void setCartInstance(Cart cartInstance) {
        this.cartInstance = cartInstance;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public boolean isBeforeExpiredTime(LocalDateTime timestamp){
        return this.expiredTime !=null && this.expiredTime.isBefore(LocalDateTime.now());
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
