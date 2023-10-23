package exercise.unit_5;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int input = -1;
            StatCalc calc = new StatCalc();

            while ((input = scanner.nextInt()) != 0) {
                calc.enter(input);
            }
            System.out.println(calc);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

class StatCalc {

    private int count;
    private double sum;
    private double squareSum;
    private double maxValue = Double.MIN_VALUE;
    private double minValue = Double.MAX_VALUE;

    public void enter(double num) {
        count++;
        sum += num;
        squareSum += num * num;

        if (num > maxValue) {
            maxValue = num;
        }

        if (num < minValue) {
            minValue = num;
        }
    }

    public int getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getMean() {
        return sum / count;
    }

    public double getStandardDeviation() {
        double mean = getMean();
        return Math.sqrt(squareSum / count - mean * mean);
    }

    public double getMax() {
        return maxValue;
    }

    public double getMin() {
        return minValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Count: " + getCount()).append("\n");
        builder.append("Sum: " + getSum()).append("\n");
        builder.append("Mean: " + getMean()).append("\n");
        builder.append("Standard Deviation: " + getStandardDeviation()).append("\n");
        builder.append("Max Value: " + getMax()).append("\n");
        builder.append("Min Value: " + getMin()).append("\n");

        return builder.toString();
    }

}