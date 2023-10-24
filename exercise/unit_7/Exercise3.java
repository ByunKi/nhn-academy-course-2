package exercise.unit_7;

import java.util.Arrays;
import java.util.Random;

public class Exercise3 {
    public static void main(String[] args) {
        int size = 100_000;
        try {
            Integer[] testIntArray = Exercise1
                    .makeRandomList(size, 1000)
                    .toArray(new Integer[size]);
            test(testIntArray, 0);
            test(testIntArray, 1);

            String[] testStringArray = makeRandomStingArray(size);
            test(testStringArray, 0);
            test(testStringArray, 1);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String[] makeRandomStingArray(int size) {
        String[] result = new String[size];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < result.length; i++) {
            result[i] = random
                    .ints(97, (122 + 1))
                    .limit(10)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        return result;
    }

    /**
     * 10000개 이상의 크기를 가진 배열의 정렬 속도를 비교할 때 유효하다.
     */
    private static <T extends Comparable<T>> void test(T[] dummy, int testCase) {
        long startTime = System.nanoTime();
        long endTime = -1;
        double second = 1000000000.0;

        switch (testCase) {
            case 0:
                Arrays.sort(dummy);
                break;

            case 1:
                selectionSort(dummy);
                break;

            default:
                throw new IllegalArgumentException("0, 1 둘 중 1개를 입력해야합니다.");
        }

        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / second);

    }

    private static <T extends Comparable<T>> void selectionSort(T[] dummy) {
        for (int lastPlace = dummy.length - 1; lastPlace > 0; lastPlace--) {
            int maxLoc = 0;
            for (int j = 1; j <= lastPlace; j++) {
                if (dummy[j].compareTo(dummy[maxLoc]) > 0) {
                    maxLoc = j;
                }
            }

            swap(dummy, maxLoc, lastPlace);
        }

    }

    private static <T> void swap(T[] array, int original, int change) {
        T temp = array[original];
        array[original] = array[change];
        array[change] = temp;
    }
}