package exercise.unit_10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Exercise4 {
    public static void main(String[] args) {
        List<Integer> test = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> test2 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> test3 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        remove(test, value -> value % 2 == 0);
        retain(test2, value -> value % 2 == 0);
        for (Integer element : test) {
            System.out.print(element + " ");
        }
        System.out.println();

        for (Integer element : test2) {
            System.out.print(element + " ");
        }
        System.out.println();

        List<Integer> test4 = collect(test3, value -> value % 2 != 0);
        for (Integer element : test4) {
            System.out.print(element + " ");
        }
        System.out.println();

        int index = find((ArrayList<Integer>) test2, value -> value % 2 == 0);
        System.out.println("test 2, 첫번째 짝수의 인덱스: " + index);
    }

    private static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.test(element)) {
                iterator.remove();
            }
        }
    }

    private static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (!pred.test(element)) {
                iterator.remove();
            }
        }
    }

    private static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        List<T> result = new ArrayList<>();
        Iterator<T> iterator = coll.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.test(element)) {
                result.add(element);
            }
        }

        return result;
    }

    private static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        for (int i = 0; i < list.size(); i++) {
            if (pred.test(list.get(i))) {
                return i;
            }
        }

        return -1;
    }

}
