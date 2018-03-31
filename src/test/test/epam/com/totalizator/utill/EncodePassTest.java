package test.epam.com.totalizator.utill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static com.epam.totalizator.utill.Encryptor.sha1Encrypt;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)

public class EncodePassTest {

    private String expected;
    private String password;

    public EncodePassTest(String expected, String password) {
        this.expected = expected;
        this.password = password;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"65BABC38639BAB28B1567E1648BE9996512D69FA", "2r3yfr1Wq"},
                {"9E0DEFC8B644A23EA294C31B145BFBC9D2E86208", "77889900Q"}
        });
    }

    @Test
    public void shal1Encode() throws Exception {
        String actual = sha1Encrypt(password);
        assertEquals(expected, actual);
    }
}
