package com.epam.totalizator.utill;

import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class Validator {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_REGEX = "[A-z0-9_-]{3,16}$";
    private static final String PASSWORD_REGEX = "[A-z0-9_-]{6,18}$";
    private static final String EMAIL_REGEX = "[A-z0-9_\\.-]+@[a-z]+\\.[a-z]{2,4}";
    private static final String FIRST_NAME_REGEX = "[A-zА-я]+";
    private static final String LAST_NAME_REGEX = "[A-zА-я]+";
    private static final String COEFFICIENT = "\\d+([.,])?\\d{0,2}";
    private static final String SCORE = "\\d{1,4}";

    private static final int MIN_AGE = 18;
    private static final String MINIMUM_AMOUNT = "0.50";
    private static final double MIN_COEFFICIENT = 1.01;

    public static boolean checkCash(BigDecimal amount, AccountInvoice accountInvoice){
        if (amount.compareTo(new BigDecimal(MINIMUM_AMOUNT)) < 0){
            return false; }
        BigDecimal ballance =  accountInvoice.getBallance();
        return ballance.compareTo(amount) >= 0;
    }

    public static boolean checkNewAccount(String login, String password, String email, String firstName, String lastName){
        boolean flag;
        flag = Pattern.matches(LOGIN_REGEX, login)
                && Pattern.matches(PASSWORD_REGEX, password)
                && Pattern.matches(EMAIL_REGEX, email)
                && Pattern.matches(FIRST_NAME_REGEX, firstName)
                && Pattern.matches(LAST_NAME_REGEX, lastName);
        return flag;
    }

    public static boolean checkAge(Date date) {
        java.util.Date currentDate = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(date);
        int birthdayYear = calendar.get(Calendar.YEAR);
        int birthdayMonth = calendar.get(Calendar.MONTH);
        int birthdayDay = calendar.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar bday = new GregorianCalendar(birthdayYear, birthdayMonth, birthdayDay);
        GregorianCalendar current = new GregorianCalendar(year, month, day);
        int years = calculateYears(bday, current);
        return years >= MIN_AGE;
    }
    private static int calculateYears(GregorianCalendar bday, GregorianCalendar current) {
        int years = current.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
        int currentMonth = current.get(Calendar.MONTH);
        int bdayMonth = bday.get(Calendar.MONTH);
        if (bdayMonth > currentMonth){
            years--;
        }else if(bdayMonth == currentMonth && bday.get(Calendar.DAY_OF_MONTH) > current.get(Calendar.DAY_OF_MONTH)){
            years--;
        }
        return years;
    }

    public static String checkCoefficient(String coefficient){
        if(Pattern.matches(COEFFICIENT, coefficient) && Double.parseDouble(coefficient) >= MIN_COEFFICIENT){
            return coefficient;
        }else {
            return null;
        }
    }

    public static boolean checkPass(String newPassword) {
        return Pattern.matches(PASSWORD_REGEX, newPassword);
    }

    public static boolean checkNewMatch(String date, String time){
        GregorianCalendar current = new GregorianCalendar();

        Calendar calendar = Calendar.getInstance();
        Date matchDate = Date.valueOf(date);
        calendar.setTime(matchDate);
        String[] values = time.split(":");

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = Integer.parseInt(values[0]);
        int minutes = Integer.parseInt(values[1]);

        GregorianCalendar match = new GregorianCalendar(year, month, day, hour, minutes);

        return match.after(current);
    }

    public static int checkScoreCount(String score) {
        if (Pattern.matches(SCORE, score)){
            return Integer.parseInt(score);
        }else {
            return -1;
        }
    }

    public static boolean checkEventDate(Event event) {
        GregorianCalendar current = new GregorianCalendar();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getStartDate());
        String[] values = event.getStartTime().toString().split(":");

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = Integer.parseInt(values[0]);
        int minutes = Integer.parseInt(values[1]);

        GregorianCalendar match = new GregorianCalendar(year, month, day, hour, minutes);
        return match.after(current);
    }
}
