import java.math.BigInteger;

public class SumFct {
    public static void main(String[] args) {
        SimpleTest.assertEquals(BigInteger.valueOf(20), SumFct.fibo(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(80), SumFct.perimeter(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(216), SumFct.perimeter(BigInteger.valueOf(7)));
        SimpleTest.assertEquals(BigInteger.valueOf(14098308), SumFct.perimeter(BigInteger.valueOf(30)));
    }

    public static BigInteger perimeter(BigInteger n) {
        return fibo(n).multiply(BigInteger.valueOf(4L));
    }

    private static BigInteger fibo(BigInteger arg) {
        if (arg.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        } else if (arg.equals(BigInteger.TWO)) {
            return BigInteger.TWO;
        }

        BigInteger first = BigInteger.ONE;
        BigInteger second = BigInteger.ONE;
        BigInteger next = first.add(second);
        BigInteger num = BigInteger.valueOf(3L);
        BigInteger sum = BigInteger.valueOf(4L);
        while (num.compareTo(arg) <= 0) {
            num = num.add(BigInteger.ONE);
            first = second;
            second = next;
            next = first.add(second);
            sum = sum.add(next);
        }
        return sum;
    }
}