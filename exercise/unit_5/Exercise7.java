package exercise.unit_5;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise7 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            NewQuiz quiz = new NewQuiz();
            quiz.solve(scanner);
            quiz.evaluate();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("범위에 맞지 않은 입력입니다.");
        }
    }
}

class NewQuiz {
    private int size;
    private IntQuestion[] questions;
    private int[] answers;

    public NewQuiz(int size) {
        this.size = size;
        this.answers = new int[this.size];
        this.questions = new IntQuestion[this.size];
        for (int i = 0; i < questions.length; i++) {
            questions[i] = getIntQuestion();
        }
    }

    public NewQuiz() {
        this(10);
    }

    private IntQuestion getIntQuestion() {
        switch (getQuestionType()) {
            case 0:
                return new NewAdditionQuestion();
            case 1:
                return new SubtractionQuestion();
            default:
                return new MultiplicationQuestion();
        }
    }

    private int getQuestionType() {
        return (int) (Math.random() * 3);

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

interface IntQuestion {
    public int RANGE = 50;

    public String getQuestion();

    public int getCordirectAnswer();
}

class NewAdditionQuestion implements IntQuestion {
    private int a;
    private int b;

    public NewAdditionQuestion() {
        this.a = (int) (Math.random() * RANGE) + 1;
        this.b = (int) (Math.random() * RANGE) + 1;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public String getQuestion() {
        StringBuilder builder = new StringBuilder();
        builder.append(getA() + " + " + getB()).append(" 는 무엇입니까? ");

        return builder.toString();
    }

    @Override
    public int getCordirectAnswer() {
        return getA() + getB();
    }

}

class SubtractionQuestion implements IntQuestion {
    private int a;
    private int b;

    public SubtractionQuestion() {
        this.a = (int) (Math.random() * RANGE) + 1;
        this.b = (int) (Math.random() * RANGE) + 1;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public String getQuestion() {
        StringBuilder builder = new StringBuilder();
        builder.append(getA() + " - " + getB()).append(" 는 무엇입니까? ");

        return builder.toString();
    }

    @Override
    public int getCordirectAnswer() {
        return getA() - getB();
    }

}

class MultiplicationQuestion implements IntQuestion {
    private int a;
    private int b;

    public MultiplicationQuestion() {
        this.a = (int) (Math.random() * RANGE) + 1;
        this.b = (int) (Math.random() * RANGE) + 1;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public String getQuestion() {
        StringBuilder builder = new StringBuilder();
        builder.append(getA() + " * " + getB()).append(" 는 무엇입니까? ");

        return builder.toString();
    }

    @Override
    public int getCordirectAnswer() {
        return getA() * getB();
    }

}