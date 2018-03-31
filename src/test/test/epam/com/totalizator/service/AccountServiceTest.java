package test.epam.com.totalizator.service;

import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.AccountService;
import com.epam.totalizator.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class AccountServiceTest {

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceTest .class.getName());

    private AccountService accountService = ServiceFactory.getInstance().getAccountService();

    @Test
    public void failRegistrationTest() {
        boolean res;
        try {
            accountService.createNewAccount("Login","pass","timati@gmail.com","Firstname", "Lastname", new Date(1990-12-10), "BYN",
                    "+3752243242", "123133", "passport");
            Assert.assertNotNull(accountService);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,"Error in failRegistrationTest");
        }

    }
}
