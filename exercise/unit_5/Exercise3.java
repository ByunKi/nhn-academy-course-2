package exercise.unit_5;

public class Exercise3 {

    public static final int LIMIT = exercise.unit_4.Exercise4.EPOCH;
    protected static StatCalc[] calcs;

    public static void main(String[] args) {
        calcs = new StatCalc[11];
        for (int i = 0; i < calcs.length; i++) {
            calcs[i] = new StatCalc();
            simulate(new PairOfDice(), i + 2);
        }

        printResult();
    }

    private static void printResult() {
        for (int i = 0; i < calcs.length; i++) {
            System.out.println("--------------- " + (i + 2) + " ---------------");
            System.out.println(calcs[i]);
        }
    }

    private static void simulate(PairOfDice pair, int criteria) {
        for (int i = 0; i < LIMIT; i++) {
            int result = 0;

            do {
                result = pair.getSum();
                pair.roll();
            } while (pair.getSum() != criteria);

            calcs[criteria - 2].enter(result);
        }
    }

}
