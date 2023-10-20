package exercise.unit_4;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise3 {

    public static final int SIDES = 6;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int input = scanner.nextInt();
            preCondition(input);
            System.out.println(simulate(input));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("범위에 맞지 않은 입력입니다.");
        }
    }

    public static void preCondition(int number) {
        if (number < 2 || number > 12) {
            throw new IllegalArgumentException();
        }
    }

    public static int simulate(int criteria) {
        int count = 0;
        int result = 0;

        do {
            result = 0;
            for (int i = 0; i < 2; i++) {
                result += roll();
            }
            count++;
        } while (result != criteria);

        return count;
    }

    public static int roll() {
        return (int) (Math.random() * SIDES) + 1;
    }
}
