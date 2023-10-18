package exercise.unit_3;

public class Exercise2 {

    public static void main(String[] args) {

        int[] array = init(10000);
        System.out.println(getMaxInArray(array));
    }

    private static int[] init(int number) {
        int[] result = new int[number];
        for (int i = 1; i <= result.length; i++) {
            result[i - 1] = Exercise2.countDivisor(i);
        }

        return result;
    }

    public static int countDivisor(int i) {
        int count = 0;

        for (int j = 1; j <= i; j++) {
            if (i % j == 0) {
                count++;
            }
        }

        return count;
    }

    public static int getMaxInArray(int[] array) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }
}
