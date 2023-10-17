package exercise.unit_2;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);) {
            String name = scanner.nextLine();

            name = name.toUpperCase();
            System.out.println("Hello, " + name + ", nice to meet you!");
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
