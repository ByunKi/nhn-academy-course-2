package exercise.unit_7;

import java.util.ArrayList;
import java.util.List;

public class Exercise1 {
    public static void main(String[] args) {
        try {
            /* 정상적인 경우 실시 */
            List<Integer> test = makeRandomList(10, 100);
            test.forEach(System.out::println);

            /* 비정상적인 경우 실시 → capacity */
            // List<Integer> test2 = makeRandomList(0, 10);
            // test2.forEach(System.out::println);

            /* 비정상적인 경우 실시 → range */
            // List<Integer> test3 = makeRandomList(10, 0);
            // test3.forEach(System.out::println);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Integer> makeRandomList(int capacity, int range) {
        preCondition(capacity, range);

        ArrayList<Integer> list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            list.add(getRandomNumber(range));
        }

        return list;
    }

    private static void preCondition(int capacity, int range) {
        if (range <= 0) {
            throw new IllegalArgumentException("난수 생성 범위 문제");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("리스트의 크기 문제");
        }
    }

    private static int getRandomNumber(int range) {
        return (int) (Math.random() * range) + 1;
    }

}
