package exercise.unit_7;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] myArray = makeIntList(scanner, 100);

            mySort(myArray);
            printArray(myArray);

        } catch (NoSuchElementException e) {
            System.out.println("올바르지 못한 입력입니다. 정수를 입력하세요.");
        } catch (IllegalArgumentException e) {
            System.out.println("입력을 계속 하려면 양의 정수를 입력, 입력을 종료하려면 0을 입력하시오.");
        }
    }

    private static int[] makeIntList(Scanner scanner, int maxSize) {
        int index = 0;
        int[] tmp = new int[maxSize];

        int input = -1;
        do {
            input = scanner.nextInt();
            if (input != 0) {
                tmp[index++] = input;
            }
        } while (input != 0 && index < maxSize);

        return Arrays.copyOf(tmp, index);
    }

    private static void mySort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[i]) {
                    swap(array, i, j);
                }
            }
        }
    }

    private static void swap(int[] array, int original, int change) {
        int temp = array[original];
        array[original] = array[change];
        array[change] = temp;
    }

    private static void printArray(int[] array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
