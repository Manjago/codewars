import java.math.BigInteger;
import java.util.Stack;

// https://www.codewars.com/kata/559a28007caad2ac4e000083
public class SumFct {
    public static void main(String[] args) {
        SimpleTest.assertEquals(BigInteger.valueOf(1), SumFct.fiboSum(BigInteger.valueOf(1)));
        SimpleTest.assertEquals(BigInteger.valueOf(2), SumFct.fiboSum(BigInteger.valueOf(2)));
        SimpleTest.assertEquals(BigInteger.valueOf(4), SumFct.fiboSum(BigInteger.valueOf(3)));
        SimpleTest.assertEquals(BigInteger.valueOf(7), SumFct.fiboSum(BigInteger.valueOf(4)));
        SimpleTest.assertEquals(BigInteger.valueOf(12), SumFct.fiboSum(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(20), SumFct.fiboSum(BigInteger.valueOf(6)));
        SimpleTest.assertEquals(BigInteger.valueOf(1), SumFct.fiboSumF(BigInteger.valueOf(1)));
        SimpleTest.assertEquals(BigInteger.valueOf(2), SumFct.fiboSumF(BigInteger.valueOf(2)));
        SimpleTest.assertEquals(BigInteger.valueOf(4), SumFct.fiboSumF(BigInteger.valueOf(3)));
        SimpleTest.assertEquals(BigInteger.valueOf(7), SumFct.fiboSumF(BigInteger.valueOf(4)));
        SimpleTest.assertEquals(BigInteger.valueOf(12), SumFct.fiboSumF(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(20), SumFct.fiboSumF(BigInteger.valueOf(6)));
        SimpleTest.assertEquals(BigInteger.valueOf(80), SumFct.perimeter(BigInteger.valueOf(5)));
        SimpleTest.assertEquals(BigInteger.valueOf(216), SumFct.perimeter(BigInteger.valueOf(7)));
        SimpleTest.assertEquals(BigInteger.valueOf(14098308), SumFct.perimeter(BigInteger.valueOf(30)));
    }

    public static BigInteger perimeter(BigInteger n) {
        return fiboSumF(n.add(BigInteger.ONE)).multiply(BigInteger.valueOf(4L));
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

    private static BigInteger fiboSumF(BigInteger arg) {
        final ForthLikeStack f = new ForthLikeStack();

        // sum, second, first
        f.push(BigInteger.ZERO);
        f.push(BigInteger.ZERO);
        f.push(BigInteger.ONE);

        BigInteger counter = BigInteger.ONE;
        while (counter.compareTo(arg) <= 0) {
            // sum, second, first

            // https://www.forth.com/starting-forth/2-stack-manipulation-operators-arithmetic/
            // OVER	( n1 n2 — n1 n2 n1 )	Copies second item to top
            f.over(); // sum, second, first -> sum, second, first, second
            f.plus(); // sum, second, first, second -> sum, second, next(first+second)
            f.rot(); // sum, second, next(first+second) -> second, next(first+second), sum
            f.over(); // second, next(first+second), sum -> second, next(first+second), sum, next(first+second)
            f.plus(); // second, next(first+second), sum, next(first+second) -> second, next(first+second), updatedSum

            // [rename] second, next(first+second), updatedSum -> first, second, sum
            f.swap(); // first, second, sum -> first, sum, second
            f.rot(); // first, sum, second -> sum, second, first

            counter = counter.add(BigInteger.ONE);
        }

        f.rot(); // sum, second, first -> second, first, sum

        return f.pop();
    }

    static class ForthLikeStack {
        private final ArrayStack<BigInteger> stack = new ArrayStack<>(new BigInteger[4]);
        void push(BigInteger arg) {
            stack.push(arg);
        }

        BigInteger pop() {
            return stack.pop();
        }

        void over() {
            final BigInteger item = stack.peek(1);
            stack.push(item);
        }

        // +	( n1 n2 — sum )	Adds
        void plus() {
            final BigInteger one = stack.pop();
            final BigInteger two = stack.pop();
            stack.push(one.add(two));
        }

        // ROT	( n1 n2 n3 — n2 n3 n1 )	Rotates third item to top
        void rot() {
            final BigInteger n3 = stack.pop();
            final BigInteger n2 = stack.pop();
            final BigInteger n1 = stack.pop();
            stack.push(n2);
            stack.push(n3);
            stack.push(n1);
        }

        // SWAP	( n1 n2 — n2 n1 )	Reverses the top two stack items
        void swap() {
            final BigInteger n2 = stack.pop();
            final BigInteger n1 = stack.pop();
            stack.push(n2);
            stack.push(n1);
        }

    }

    static class ArrayStack<E> {
        private final E[] data;
        private int index = 0;
        public ArrayStack(E[] data) {
            this.data = data;
        }

        void push(E item) {
            data[index++] = item;
        }

        E pop() {
            return data[--index];
        }

        E peek(int shift) {
            return data[index - 1 - shift];
        }
    }
}