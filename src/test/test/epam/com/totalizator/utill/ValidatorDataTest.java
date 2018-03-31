package test.epam.com.totalizator.utill;

import com.epam.totalizator.entity.AccountInvoice;
import com.epam.totalizator.entity.Event;
import com.epam.totalizator.utill.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ValidatorDataTest {

    @Test
    public void testNewAccount(){
        String login = "Login";
        String password = "Password";
        String email = "email@gmail.com";
        String firstName = "Demis";
        String secondName = "Karibidis";
        Assert.assertTrue(Validator.checkNewAccount(login,password,email,firstName,secondName));
    }

    @Test
    public void testCheckMoney(){
        AccountInvoice accountInvoice = new AccountInvoice();
        accountInvoice.setBallance(new BigDecimal("200"));
        Assert.assertTrue(Validator.checkCash(new BigDecimal("5"), accountInvoice));
    }
    @Test
    public void checkAgeTrueTest(){
        Date date = Date.valueOf("1994-12-12");
        boolean age = Validator.checkAge(date);
        Assert.assertTrue(age);
    }

    @Test
    public void checkAgeFalseTest(){
        Date date1 = Date.valueOf("2005-11-11");
        Assert.assertFalse(Validator.checkAge(date1));
    }
    @Test
    public void testValidatePassword(){
        String password = "10101010";
        Assert.assertTrue(Validator.checkPass(password));
    }
    @Test
    public void testCheckFalseCoefficient(){
        String coef = "0.1";
        Assert.assertEquals(coef, Validator.checkCoefficient(coef));
    }
    @Test
    public void testCheckScore(){
        String score = "0";
        Assert.assertEquals(0, Validator.checkScoreCount(score));
    }
    @Test
    public void testCheckNewMatch(){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MINUTE, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        StringBuilder date = new StringBuilder();
        date.append(year).append("-").append(month).append("-").append(day);
        StringBuilder time = new StringBuilder();
        time.append(hour).append(":").append(minutes);
        Assert.assertFalse(Validator.checkNewMatch(date.toString(), time.toString()));
    }

    @Test
    public void testCheckBetTime(){
        Event event = new Event();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, -1);
        Date date = new Date(calendar.getTime().getTime());
        event.setStartDate(date);
        event.setStartTime(new Time(date.getTime()));
        Assert.assertFalse(Validator.checkEventDate(event));
    }
}
