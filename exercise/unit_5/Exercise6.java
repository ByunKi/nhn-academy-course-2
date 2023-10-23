package exercise.unit_5;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise6 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Quiz quiz = new Quiz();
            quiz.solve(scanner);
            quiz.evaluate();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("범위에 맞지 않은 입력입니다.");
        }
    }
}

class Quiz {
    private int size;
    private AdditionQuestion[] questions;
    private int[] answers;

    public Quiz(int size) {
        this.size = size;
        this.answers = new int[this.size];
        this.questions = new AdditionQuestion[this.size];
        for (int i = 0; i < questions.length; i++) {
            questions[i] = new AdditionQuestion();
        }
    }

    public Quiz() {
        this(10);
    }

    public void solve(Scanner scanner) {
        for (int i = 0; i < questions.length; i++) {
            System.out.print((i + 1) + ". " + questions[i].getQuestion());
            answers[i] = scanner.nextInt();
        }
    }

    public void evaluate() {
        int count = 0;
        for (int i = 0; i < questions.length; i++) {
            if (answers[i] == questions[i].getCordirectAnswer()) {
                count++;
            }
        }

        System.out.println("점수: " + (count * 10));
    }
}

class AdditionQuestion {
    private int a;
    private int b;

    public AdditionQuestion() {
        this.a = (int) (Math.random() * 50) + 1;
        this.b = (int) (Math.random() * 50) + 1;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getQuestion() {
        StringBuilder builder = new StringBuilder();
        builder.append(getA() + " + " + getB()).append(" 는 무엇입니까? ");

        return builder.toString();
    }

    public int getCordirectAnswer() {
        return getA() + getB();
    }

}