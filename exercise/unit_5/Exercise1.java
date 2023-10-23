package exercise.unit_5;

public class Exercise1 {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int count = 0;
        PairOfDice pair = new PairOfDice();

        do {
            count++;
            pair.roll();
        } while (pair.getSum() != 2);

        System.out.println(count);
    }
}

class PairOfDice {

    private int die1;
    private int die2;

    public PairOfDice(int val1, int val2) {
        this.die1 = val1;
        this.die2 = val2;
    }

    public PairOfDice() {
        roll();
    }

    public int getDie1() {
        return die1;
    }

    public void setDie1(int val1) {
        preCondition(val1);
        die1 = val1;
    }

    public int getDie2() {
        return die2;
    }

    public void setDie2(int val2) {
        preCondition(val2);
        die2 = val2;
    }

    public int getSum() {
        return getDie1() + getDie2();
    }

    public void roll() {
        setDie1((int) (Math.random() * 6) + 1);
        setDie2((int) (Math.random() * 6) + 1);
    }

    private void preCondition(int number) {
        if (number > 7 || number < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "die1: " + die1 + ", die2: " + die2;

    }
}