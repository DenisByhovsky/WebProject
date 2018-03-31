package test.epam.com.totalizator.utill;

import org.testng.annotations.Test;
import java.io.InputStream;
import java.util.Properties;
import org.testng.Assert;

public class PropertyTest {
    private static final String DB_CONFIG = "pr.properties";
    private static final String CONFIG_PROP = "pages.properties";
    private static final String LANGUAGE_EN = "text_en.properties";
    private static final String LANGUAGE_RU = "text_ru.properties";


    @Test
    public void databasePropTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(DB_CONFIG);
        properties.load(is);
        Assert.assertFalse(properties.isEmpty());
    }

    @Test
    public void configPropTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(CONFIG_PROP);
        properties.load(is);
        Assert.assertFalse(properties.isEmpty());
    }


    @Test
    public void languagePropTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(LANGUAGE_EN);
        properties.load(is);
        Assert.assertFalse(properties.isEmpty());
    }

    @Test
    public void languagePropertyTest() throws Exception{
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream(LANGUAGE_RU);
        properties.load(is);
        Assert.assertFalse(properties.isEmpty());
    }
}
