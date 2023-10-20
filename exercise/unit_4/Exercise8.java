package exercise.unit_4;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise8 {

    public static final int SIZE = 10;

    protected static int[][] quizzes = new int[SIZE][2];
    protected static int[] answers = new int[SIZE];
    protected static boolean[] isCorrects = new boolean[SIZE];

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("출제 범위를 입력하세요: 1 ~ ");
            int upperBound = scanner.nextInt();

            generateProblems(upperBound);
            solve(scanner);
            System.out.println("결과는 " + evaluate() + " / " + SIZE);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("범위에 맞지 않은 입력입니다.");
        }

    }

    private static void generateProblems(int upperBound) {
        for (int i = 0; i < SIZE; i++) {
            quizzes[i][0] = makeRandomNumber(upperBound);
            quizzes[i][1] = makeRandomNumber(upperBound);
        }
    }

    private static int makeRandomNumber(int upperBound) {
        preConditionAtGenerateProblems(upperBound);
        return (int) (Math.random() * upperBound) + 1;
    }

    private static void preConditionAtGenerateProblems(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void solve(Scanner scanner) {
        for (int i = 0; i < SIZE; i++) {
            int[] quiz = getQuiz(i);
            System.out.print(quiz[0] + " + " + quiz[1] + " = ");
            answers[i] = scanner.nextInt();
        }
    }

    private static int[] getQuiz(int index) {
        preConditionAtGetQuiz(index);
        return quizzes[index];
    }

    private static void preConditionAtGetQuiz(int number) {
        if (number < 0 || number >= SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private static int evaluate() {
        for (int i = 0; i < SIZE; i++) {
            if (answers[i] == quizzes[i][0] + quizzes[i][1]) {
                isCorrects[i] = true;
            }
        }

        return getScore();
    }

    private static int getScore() {
        int count = 0;
        for (int i = 0; i < answers.length; i++) {
            if (isCorrects[i]) {
                count++;
            }
        }

        return count;
    }
}
