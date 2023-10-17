package exercise.unit_2;

public class Exercise2 {
    public static final int TRIALS = 1000;
    public static final int SIDES = 6;

    public static void main(String[] args) {

        int[] numbers = new int[SIDES];
        for (int i = 0; i < TRIALS; i++) {
            int index = (int) (Math.random() * SIDES) + 1;
            numbers[index - 1]++;
        }

        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}
