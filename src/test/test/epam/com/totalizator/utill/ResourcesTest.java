package test.epam.com.totalizator.utill;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ResourceBundle;

public class ResourcesTest {

    @Test
    public void testResources(){
        Assert.assertNotNull(ResourceBundle.getBundle("pages"));
    }

    @Test
    public void testResourcesDB(){
        Assert.assertNotNull(ResourceBundle.getBundle("pr"));
    }
}

