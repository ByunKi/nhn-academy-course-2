package exercise.unit_2;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise4 {
    public static final double RATIO_DIME = 0.5;
    public static final double RATIO_NICKEL = 0.25;
    public static final double RATIO_PENNIES = 0.125;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);) {
            int dime = scanner.nextInt();
            int nickel = scanner.nextInt();
            int pennies = scanner.nextInt();

            double result = dime * RATIO_DIME + nickel * RATIO_NICKEL + pennies * RATIO_PENNIES;
            System.out.println(result);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
