package exercise.unit_2;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise5 {
    public static final int GROSS = 144;
    public static final int DOZEN = 12;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);) {
            int eggs = scanner.nextInt();
            int numbersOfGross = eggs / GROSS;
            int numbersOfDozen = (eggs % GROSS) / DOZEN;
            int numbersOfLeftOver = (eggs % GROSS) % DOZEN;

            System.out.println(numbersOfGross + " " + numbersOfDozen + " " + numbersOfLeftOver);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
