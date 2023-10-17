import java.math.BigInteger;
import java.util.Random;

public class GuillouQuisquater {
    public static void main(String[] args) throws IllegalAccessException {
        if (args.length == 0) {
            System.out.println("Входные параметры отсутсвуют");
            return;
        }
        if (args[0].equals("/help") || args[0].equals("h")) {
            System.out.println("""
                    Программе должны передаваться следующие параметры:
                    \t- Число J (открытый ключ)
                    \t- 2 простых числа p и q""");
            return;
        }
        if (args.length < 3) {
            System.out.println("Передано некорректное число параметров");
            return;
        }
        if (args[1].equals(args[2])) {
            System.out.println("Числа p и q должны быть различными");
            return;
        }
        BigInteger j, v, n, phi;
        try {
            Random rnd = new Random();
            j = new BigInteger(args[0]);
            BigInteger p = new BigInteger(args[1]);
            BigInteger q = new BigInteger(args[2]);
            if (!p.isProbablePrime(100) || !q.isProbablePrime(100)) {
                System.out.println("Числа p и q должны быть простыми.");
                throw new IllegalArgumentException();
            }
            phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
            v = new BigInteger(phi.bitLength(), rnd).mod(phi);
            while (!v.gcd(phi).equals(BigInteger.ONE)) {
                v = v.add(BigInteger.ONE).mod(phi);
                if (v.equals(BigInteger.ZERO)) {
                    v = BigInteger.ONE;
                }
            }
            n = p.multiply(q);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка в чтении входных параметров.");
            return;
        }
        GQService service = new GQService(j, v, n, phi);
    }
}