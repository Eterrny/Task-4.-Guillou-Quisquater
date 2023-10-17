import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class PeggyTest {
    Peggy peggy;

    @BeforeEach
    void prepareData() {
        peggy = new Peggy(new BigInteger("10"), new BigInteger("19"), new BigInteger("221"), new BigInteger("192"));
    }

    @Test
    void testToString() {
        Assertions.assertTrue(peggy.toString().startsWith("""
                Открытая информация Пегги: J = 10, v = 19, n = 221.
                Закрытый ключ B = """));
    }

    @Test
    void testSetB() {
        Assertions.assertEquals(peggy.getJ().multiply(peggy.getB().modPow(peggy.getV(), peggy.getN())).mod(peggy.getN()), BigInteger.ONE);
    }
}