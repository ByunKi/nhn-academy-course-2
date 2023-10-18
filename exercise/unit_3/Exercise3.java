package exercise.unit_3;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            String[] inputs = scanner.nextLine().split(" ");
            double operandA = Double.parseDouble(inputs[0]);
            double operandB = Double.parseDouble(inputs[2]);
            String operator = inputs[1];

            System.out.println(
                    operandA + " " + operator + " " + operandB + " = " + calculate(operandA, operator, operandB));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("산술 연산자를 입력하세요.");
        }
    }

    public static double calculate(double A, String operator, double B) {
        switch (operator) {
            case "+":
                return A + B;

            case "-":
                return A - B > 0 ? A - B : B - A;

            case "*":
                return A * B;

            case "/":
                if (A > B) {
                    if (B == 0) {
                        throw new ArithmeticException();
                    }
                    return A / B;
                } else if (A == B) {
                    return 1;
                } else {
                    if (A == 0) {
                        throw new ArithmeticException();
                    }
                    return B / A;
                }

            default:
                throw new IllegalArgumentException();
        }
    }
}
