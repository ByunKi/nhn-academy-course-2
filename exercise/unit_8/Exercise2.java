package exercise.unit_8;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = "";
            do {
                input = scanner.nextLine().trim();
                if (!input.equals(" ")) {
                    simulate(new BigInteger(input));
                }
            } while (!input.equals(" "));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("10진수 숫자를 입력해주세요.");
        }
    }

    private static void simulate(BigInteger integer) {
        BigInteger three = BigInteger.valueOf(3);
        while (!integer.equals(BigInteger.ONE)) {
            if (integer.testBit(0)) {
                integer = integer.multiply(three).add(BigInteger.ONE);
            } else {
                integer = integer.divide(BigInteger.TWO);
            }
            System.out.println(integer);
        }
    }
}
