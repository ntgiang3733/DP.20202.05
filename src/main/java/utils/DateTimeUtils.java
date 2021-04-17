package utils;

import common.exception.InvalidCardException;

import java.util.Calendar;

public class DateTimeUtils {

    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;
    public static final int MAX_YEAR_EXPIRED = 99;

    private DateTimeUtils() {
    }

    static public int getCurrentYearExpired() {
        return Calendar.getInstance().get(Calendar.YEAR) % 100;
    }

    static public String getExpiredDate(String strMonth, String strYear) throws Exception {
        String expirationDate = null;
        int month = -1;
        int year = -1;

        try {
            month = Integer.parseInt(strMonth);
            year = Integer.parseInt(strYear);
            int currentYear = getCurrentYearExpired();
            if (month < MIN_MONTH || month > MAX_MONTH || year < currentYear || year > MAX_YEAR_EXPIRED) {
                throw new Exception();
            }
            expirationDate = strMonth + strYear;

        } catch (Exception ex) {
            throw new Exception();
        }
        return expirationDate;
    }

    static public String[] getMonthYearFromString(String date) throws Exception {
        String[] strs = date.split("/");
        if (strs.length != 2) {
            throw new Exception();
        }
        ;
        return strs;
    }
}
