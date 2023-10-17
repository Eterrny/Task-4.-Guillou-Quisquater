import java.math.BigInteger;
import java.security.SecureRandom;

public class Peggy {
    private final BigInteger v, n, phi;
    private BigInteger j, b, r, d;

    public Peggy(BigInteger j, BigInteger v, BigInteger n, BigInteger phi) {
        this.j = j;
        this.v = v;
        this.n = n;
        this.phi = phi;
        SecureRandom rnd = new SecureRandom();
        do {
            r = new BigInteger(n.bitLength(), rnd).mod(n);
        } while (r.equals(BigInteger.ZERO));
        this.setB();
    }

    @Override
    public String toString() {
        return String.format("""
                Открытая информация Пегги: J = %d, v = %d, n = %d.
                Закрытый ключ B = %d""", j, v, n, b);
    }

    public BigInteger calculateD() {
        return r.multiply(b.modPow(d, n)).mod(n);
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getJ() {
        return j;
    }

    public BigInteger getV() {
        return v;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getB() {
        return b;
    }

    private void setB() {
        try {
            this.b = j.modInverse(n).modPow(v.modInverse(phi), n);
        } catch (ArithmeticException e) {
            j = j.add(BigInteger.ONE);
            setB();
        }
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
}
