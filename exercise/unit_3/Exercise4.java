package exercise.unit_3;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise4 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            input = input.replaceAll("[\\\"\\.,]", "");

            String[] inputs = input.split("['\\s]");
            for (String element : inputs) {
                System.out.println(element);
            }

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
