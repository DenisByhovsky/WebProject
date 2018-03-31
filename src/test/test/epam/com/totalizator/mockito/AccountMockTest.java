package test.epam.com.totalizator.mockito;

import com.epam.totalizator.entity.Account;
import com.epam.totalizator.exception.ServiceException;
import com.epam.totalizator.service.impl.AccountServiceImpl;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class AccountMockTest {

    private final String PASS="tye67we";
    @Test
    public void AccountMockTest() throws ServiceException {

        AccountServiceImpl twitterClient = new AccountServiceImpl();
        Account acc = mock(Account.class);
        when(acc.getLogin()).thenReturn("Using mockito is great");
        twitterClient.loginAccount(acc.getLogin(),PASS);
        verify(acc, atLeastOnce()).getLogin();
    }
}