import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class GQServiceTest {

    @Test
    public void unitTest1() {
        BigInteger j = new BigInteger("123");
        BigInteger v = new BigInteger("10");
        BigInteger n = new BigInteger("1024");
        BigInteger phi = new BigInteger("13");
        java.lang.IllegalAccessException thrown = assertThrows(
                java.lang.IllegalAccessException.class,
                () -> new GQService(j, v, n, phi),
                "Ожидалось исключение в new GQService(...), но его не было."
        );
    }

    @Test
    public void unitTest2() throws IllegalAccessException {
        BigInteger j = new BigInteger("10");
        BigInteger v = new BigInteger("19");
        BigInteger n = new BigInteger("221");
        BigInteger phi = new BigInteger("192");
        GQService service = new GQService(j, v, n, phi);
        Victor victor = service.getVictor();
        Assertions.assertEquals(victor.getT_(), victor.getT());
    }
}