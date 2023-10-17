package exercise.unit_2;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise6 {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);) {
            System.out.println("Please enter your first name and last name, separated by a space.");
            System.out.print("? ");
            String[] names = scanner.nextLine().split(" ");

            System.out.println("Your first name is " + names[0] + ", which has " + names[0].length() + " characters");
            System.out.println("Your last name is " + names[1] + ", which has " + names[1].length() + " characters");
            System.out.println("Your initials are " + names[0].charAt(0) + names[1].charAt(0));

        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
