package test.epam.com.totalizator.utill;

import org.junit.Assert;
import org.junit.Test;
import static com.epam.totalizator.manager.ManagerHandler.LOGIN_PAGE;
import static com.epam.totalizator.manager.ManagerHandler.MAIN_PAGE;
import static com.epam.totalizator.manager.ManagerHandler.getPage;

public class PageResourcesTest {

    @Test
    public void getPropertyTest() {
        Assert.assertEquals("/index.jsp", getPage(MAIN_PAGE));
        Assert.assertEquals("/jsp/login.jsp", getPage(LOGIN_PAGE));
    }
}
