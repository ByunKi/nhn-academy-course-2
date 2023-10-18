package exercise.unit_3;

public class Exercise1 {

    public static void main(String[] args) {
        int count = 0;

        while (true) {
            int diceA = rollDice();
            int diceB = rollDice();
            count++;

            if (diceA == 1 && diceA == diceB) {
                System.out.println(count);
                break;
            }
        }
    }

    public static int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
}
