package controller;

import common.exception.ExpiredSessionException;
import common.exception.FailLoginException;
import dao.user.UserDAO;
import entity.user.User;
import utils.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author
 */
//SOLID: vi pham nguyen li LSP vi class nay khong dung den cac method trong BaseController
// singleton
//
public class AuthenticationController extends BaseController {


    private static AuthenticationController instance;

    public static AuthenticationController getInstance() {
        if (instance == null) {
            instance = new AuthenticationController();
        }
        return instance;
    }


    public boolean isAnonymousSession() {
        try {
            getMainUser();
            return false;
        } catch (Exception ex) {
            return true;
        }
    }

    // cleancode : sua ten bien de hieu
    // cleancode: chuyen cac phuong thuc so sanh vao trong method
	/*
	public User getMainUser() throws ExpiredSessionException {
		// common coupling: dung bien toan cuc mainUser, expiredTime
		if (SessionInformation.getInstance().getMainUser() == null
				|| SessionInformation.getInstance().getExpiredTime() == null
				|| SessionInformation.getInstance().getExpiredTime().isBefore(LocalDateTime.now())) {
			logout();
			throw new ExpiredSessionException();
		} else
			return SessionInformation.getInstance().getMainUser().cloneInformation();// common coupling: dung bien toan
																						// cuc mainUser
	}
*/
    public User getMainUser() throws ExpiredSessionException {
        SessionInformation sessionInformation = SessionInformation.getInstance();

        if (!sessionInformation.hasMainUser() || sessionInformation.isBeforeExpiredTime(LocalDateTime.now())) {
            logout();
            throw new ExpiredSessionException();
        } else
            return sessionInformation.getMainUser().cloneInformation();// common coupling: dung bien toan cuc mainUser
    }

    // data coupling
    public void login(String email, String password) throws Exception {
        try {
            User user = new UserDAO().authenticate(email, md5(password));
            // cleancode: chuyen phuong thuc check null vao authenticate
            /*if (Objects.isNull(user))
                throw new FailLoginException();*/
            SessionInformation.getInstance().setMainUser(user);
            // common coupling: dung bien toan cuc mainUser
            // cleancode: ko su dung magic-number
            // SessionInformation.getInstance().setExpiredTime(LocalDateTime.now().plusHours(24));
            SessionInformation.getInstance().setExpiredTime(LocalDateTime.now().plusHours(SessionInformation.TIME_SESSION_EXPIRED_HOUR));
            // common coupling: dung bien toan cuc expiredTime
        } catch (SQLException ex) {
            throw new FailLoginException();
        }
    }

    public void logout() {
        SessionInformation.getInstance().setMainUser(null);//common coupling: dung bien toan cuc mainUser
        SessionInformation.getInstance().setExpiredTime(null);//common coupling: dung bien toan cuc expiredTime
    }

    /**
     * Return a {@link String String} that represents the cipher text
     * encrypted by md5 algorithm.
     *
     * @param message - plain text as {@link String String}.
     * @return cipher text as {@link String String}.
     */
    //coupling data
    // cleancode: return ngay khi co the
    // cleancode: ko su dung magic_number
    /*
    private String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            digest = "";
        }
        return digest;
    }*/
    private String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format(Utils.DECIMAL_FORMAT_NUMBER, b & Utils.HEXA_LENGTH_VALUE));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Utils.getLogger(Utils.class.getName());
            return "";
        }
    }
}
