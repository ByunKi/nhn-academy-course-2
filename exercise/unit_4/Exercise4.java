package exercise.unit_4;

public class Exercise4 {

    public static final int EPOCH = 10000;

    public static void main(String[] args) {
        try {
            printResult();
        } catch (IllegalArgumentException e) {
            System.out.println("범위에 맞지 않은 입력입니다.");
        }
    }

    private static void printResult() {
        System.out.println("Total On Dice \tAverage Number of Rolls");
        for (int i = 2; i <= 12; i++) {
            simulate(i);
        }
    }

    private static void simulate(int criteria) {
        double totalRollCount = 0d;
        for (int i = 0; i < EPOCH; i++) {
            totalRollCount += Exercise3.simulate(criteria);
        }
        System.out.println(criteria + "\t\t" + (totalRollCount / EPOCH));
    }
}
