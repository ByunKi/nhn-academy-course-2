package exercise.unit_8;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = "";

            while (true) {
                System.out.print("종료하려면 0을 입력하세요: ");
                input = scanner.nextLine();
                if (input.equals("0")) {
                    break;
                }
                equationCalculate(scanner);
            }

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void equationCalculate(Scanner scanner) {
        System.out.print("방정식에 들어갈 숫자 3개를 입력하시오: ");
        double A = scanner.nextDouble();
        double B = scanner.nextDouble();
        double C = scanner.nextDouble();
        scanner.nextLine();

        System.out.println(root(A, B, C));
    }

    public static double root(double A, double B, double C)
            throws IllegalArgumentException {
        if (A == 0) {
            throw new IllegalArgumentException("A can't be zero.");
        } else {
            double disc = B * B - 4 * A * C;
            if (disc < 0)
                throw new IllegalArgumentException("Discriminant < zero.");
            return (-B + Math.sqrt(disc)) / (2 * A);
        }
    }

}