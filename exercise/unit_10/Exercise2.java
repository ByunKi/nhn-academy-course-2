package exercise.unit_10;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Exercise2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] inputs = scanner.nextLine().split("\\]");
            char operator = inputs[1].charAt(1);
            Set<Integer> s1 = makeSet(inputs[0]);
            Set<Integer> s2 = makeSet(inputs[1]);

            calculate(s1, operator, s2);
            for (Integer element : s1) {
                System.out.print(element + " ");
            }

        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static TreeSet<Integer> makeSet(String data) {
        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);
            if (Character.isDigit(ch)) {
                result.add(Integer.parseInt(String.valueOf(ch)));
            }
        }

        return result;
    }

    private static void calculate(Set<Integer> s1, char operator, Set<Integer> s2) {
        switch (operator) {
            case '+':
                s1.addAll(s2);
                break;
            case '*':
                s1.retainAll(s2);
                break;
            case '-':
                s1.removeAll(s2);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
