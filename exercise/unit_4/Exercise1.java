package exercise.unit_4;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            printCapitalized(input);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printCapitalized(String str) {
        StringBuilder builder = new StringBuilder();

        String[] array = str.split(" ");
        for (int i = 0; i < array.length; i++) {
            if (Character.isLowerCase(array[i].charAt(0))) {
                builder.append(Character.toUpperCase(array[i].charAt(0)))
                        .append(array[i].substring(1))
                        .append(" ");
            } else {
                builder.append(array[i]).append(" ");
            }
        }

        System.out.println(builder.deleteCharAt(builder.length() - 1).toString());
    }
}