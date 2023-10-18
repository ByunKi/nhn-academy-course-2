package exercise.unit_3;

public class Exercise6 {

    public static void main(String[] args) {

        int[] array = init(10000);
        int target = Exercise2.getMaxInArray(array);

        System.out.println(target);
        printDivisors(array, target);

    }

    private static int[] init(int number) {
        int[] result = new int[number];
        for (int i = 1; i <= result.length; i++) {
            result[i - 1] = Exercise2.countDivisor(i);
        }

        return result;
    }

    public static void printDivisors(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                System.out.println(i + 1);
            }
        }
    }
}
