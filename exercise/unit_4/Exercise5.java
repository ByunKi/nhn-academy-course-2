package exercise.unit_4;

public class Exercise5 {
    public static void main(String[] args) {
        double[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        System.out.println(counter(2).apply(values));
        System.out.println(max().apply(values));
        System.out.println(min().apply(values));
        System.out.println(sum().apply(values));
        System.out.println(average().apply(values));
    }

    public static ArrayProcessor counter(double value) {
        return (values -> {
            double count = 0;
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) {
                    count++;
                }
            }

            return count;
        });
    }

    public static ArrayProcessor max() {
        return (values -> {
            double max = Double.MIN_VALUE;
            for (int i = 0; i < values.length; i++) {
                if (values[i] > max) {
                    max = values[i];
                }
            }

            return max;
        });
    }

    public static ArrayProcessor min() {
        return (values -> {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < values.length; i++) {
                if (values[i] < min) {
                    min = values[i];
                }
            }

            return min;
        });
    }

    public static ArrayProcessor sum() {
        return (values -> {
            int result = 0;
            for (int i = 0; i < values.length; i++) {
                result += values[i];
            }

            return result;
        });
    }

    public static ArrayProcessor average() {
        return (values -> sum().apply(values) / values.length);
    }
}

@FunctionalInterface
interface ArrayProcessor {
    double apply(double[] array);
}