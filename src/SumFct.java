import java.math.BigInteger;
// https://www.codewars.com/kata/559a28007caad2ac4e000083
public class SumFct {
    public static void main(String[] args) {
        SimpleTest.assertEquals(BigInteger.valueOf(1), SumFct.fiboSum(BigInteger.valueOf(1)));
        SimpleTest.assertEquals(BigInteger.valueOf(2), SumFct.fiboSum(BigInteger.valueOf(2)));
        SimpleTest.assertEquals(BigInteger.valueOf(4), SumFct.fiboSum(BigInteger.valueOf(3)));
        SimpleTest.assertEquals(BigInteger.valueOf(7), SumFct.fiboSum(BigInteger.valueOf(4)));
        SimpleTest.assertEquals(BigInteger.valueOf(12), SumFct.fiboSum(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(20), SumFct.fiboSum(BigInteger.valueOf(6)));
        SimpleTest.assertEquals(BigInteger.valueOf(80), SumFct.perimeter(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(216), SumFct.perimeter(BigInteger.valueOf(7)));
        SimpleTest.assertEquals(BigInteger.valueOf(14098308), SumFct.perimeter(BigInteger.valueOf(30)));
    }

    public static BigInteger perimeter(BigInteger n) {
        return fiboSum(n.add(BigInteger.ONE)).multiply(BigInteger.valueOf(4L));
    }

    // 1 1 2 3 5  8
    // 1 2 4 7 12 20
    private static BigInteger fiboSum(BigInteger arg) {

        BigInteger first = BigInteger.ONE;
        BigInteger second = BigInteger.ZERO;
        BigInteger next; // any
        BigInteger sum = BigInteger.ZERO;

        BigInteger counter = BigInteger.ONE;
        while (counter.compareTo(arg) <= 0) {
            next = first.add(second);
            sum = sum.add(next);
            first = second;
            second = next;
            counter = counter.add(BigInteger.ONE);
        }

        return sum;
    }
}