package exercise.unit_4;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int result = 0;
            String input = scanner.nextLine();
            for (int i = 0; i < input.length(); i++) {
                int value = hexValue(input.charAt(i));
                result = calculate(result, value);
            }
            System.out.println(result);

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int calculate(int result, int value) {
        if (!preCondition(value)) {
            return -1;
        }

        return result * 16 + value;
    }

    private static boolean preCondition(int value) {
        return value > 0;
    }

    private static int hexValue(char ch) {
        if (Character.isDigit(ch)) {
            return Integer.valueOf(ch);
        }
        return letterToValue(ch);
    }

    private static int letterToValue(char ch) {
        ch = Character.isUpperCase(ch) ? ch : Character.toUpperCase(ch);

        switch (ch) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return -1;
        }
    }
}
