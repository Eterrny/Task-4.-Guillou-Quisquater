import java.math.BigInteger;

public class GQService {
    public Peggy peggy;
    public Victor victor;

    public GQService(BigInteger j, BigInteger v, BigInteger n, BigInteger phi) throws IllegalAccessException {
        this.peggy = new Peggy(j, v, n, phi);
        System.out.println(peggy);
        this.victor = new Victor(v);
        this.step1();
    }

    private void step1() throws IllegalAccessException {
        BigInteger T = peggy.getR().modPow(peggy.getV(), peggy.getN());
        victor.setT(T);
        System.out.printf("""
                \n--- Шаг 1 ---
                Пегги выбрала случайное число r = %d
                Вычислила Т = %d и отправила его Виктору
                                
                        """, peggy.getR(), T
        );
        this.step2();
    }

    private void step2() throws IllegalAccessException {
        peggy.setD(victor.getRandomInt());
        System.out.printf("""
                \n--- Шаг 2 ---
                Виктор выбрал случайное число d = %d и послал его Пегги
                                
                        """, victor.getRandomInt()
        );
        this.step3();
    }

    private void step3() throws IllegalAccessException {
        BigInteger D = peggy.calculateD();
        victor.setD(D);
        System.out.printf("""
                \n--- Шаг 3 ---
                Пегги вычислила D = %d и послала его Виктору
                                
                        """, D
        );
        this.step4();
    }

    private void step4() throws IllegalAccessException {
        victor.calculateT_(peggy);
        if (!victor.getT().equals(victor.getT_())) {
            throw new IllegalAccessException("Подлинность Пегги недоказана, так как T' != T\nT = " + victor.getT() + ", T' = " + victor.getT_());
        }
        System.out.printf("""
                \n--- Шаг 4 ---
                Виктор вычислил Т' = %d.
                T' = T, следовательно подлинность Пегги доказана.
                                
                        """, victor.getT_()
        );
    }

    public Victor getVictor() {
        return victor;
    }
}
