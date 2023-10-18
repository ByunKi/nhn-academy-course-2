package exercise.unit_3;

/* 
 * 1. 생일이 같은 사람 3명이 되었을 때, 전체 탐색의 수
 * 2. 365번의 무작위적인 수행 끝에 기록된 사람의 숫자는?
 * 3. 생일이 중복인 경우를 찾았을 때, 연산의 시행 횟수는?
 */

public class Exercise7 {

    public static void main(String[] args) {

        Simulator case1 = new Simulator(365, 3);
        case1.doComplimentaryEpoch();
        System.out.println();

        case1.simulate();
        System.out.println(case1);

        Simulator case2 = new Simulator(365, 1);
        case2.simulate();
        System.out.println(case2);
    }
}

class Simulator {
    private int[] board;
    private int count;
    private int range;
    private int criteria;

    public Simulator(int range, int criteria) {
        preCondition(range, criteria);
        this.range = range;
        this.criteria = criteria;
        this.count = 0;
        this.board = new int[range];
    }

    private static void preCondition(int range, int criteria) {
        if (range <= 0 || criteria < 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getCriteria() {
        return criteria;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRange() {
        return range;
    }

    public int getValue() {
        return (int) (Math.random() * getRange());
    }

    public void doComplimentaryEpoch() {
        for (int i = 0; i < getRange(); i++) {
            int value = getValue();
            conduct(value);
        }

        System.out.println("Total: " + getRange() + ", Result: " + getCountedNumber());
        clear();
    }

    private int getCountedNumber() {
        int result = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] != 0) {
                result++;
            }
        }

        return result;
    }

    public void clear() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                continue;
            }

            board[i] = 0;
        }

        setCount(0);
    }

    public void simulate() {
        while (true) {
            int value = getValue();
            conduct(value);

            if (predicate(value)) {
                return;
            }
        }

    }

    private void conduct(int value) {
        this.board[value] += 1;
        setCount(getCount() + 1);
    }

    private boolean predicate(int value) {
        return this.board[value] == getCriteria() + 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("  Trials: ").append(getCount()).append("\n");
        builder.append("   Range: ").append(getRange()).append("\n");
        builder.append("Criteria: ").append(getCriteria()).append("\n");
        builder.append(" Checked: ").append(getCountedNumber()).append("\n");

        return builder.toString();
    }

}