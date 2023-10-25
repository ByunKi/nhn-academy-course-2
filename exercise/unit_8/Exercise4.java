package exercise.unit_8;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine().trim();
            solution(input, scanner.nextDouble(), "x");
        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void solution(String input, double value, String ch) {
        if (!input.contains(ch)) {
            System.out.println(Double.NaN);
        } else {
            Expr expr = new Expr(input);
            System.out.println(expr + " = " + expr.value(value));
        }
    }
}
