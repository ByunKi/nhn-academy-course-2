package exercise.unit_9;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int input = scanner.nextInt();
            if (input < 0) {
                throw new IllegalArgumentException("입력받은 정수는 0 이상의 값이어야 합니다.");
            }

            System.out.println("팩토리얼: " + factorial(BigInteger.valueOf(input)));
            System.out.println("피보나치: " + fibonacci(BigInteger.valueOf(input)));

        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static BigInteger factorial(BigInteger init) {
        if (init.equals(BigInteger.ZERO)) {
            return BigInteger.ONE;
        }
        return init.multiply(factorial(init.subtract(BigInteger.ONE)));
    }

    private static BigInteger fibonacci(BigInteger init) {
        if (init.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        }
        return fibonacci(init.subtract(BigInteger.ONE)).add(fibonacci(init.subtract(BigInteger.TWO)));
    }
}
