package exercise.unit_10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise5 {
    private static ScoreInfo[] scoreData = new ScoreInfo[] {
            new ScoreInfo("Smith", "John", 70),
            new ScoreInfo("Doe", "Mary", 85),
            new ScoreInfo("Page", "Alice", 82),
            new ScoreInfo("Cooper", "Jill", 97),
            new ScoreInfo("Flintstone", "Fred", 66),
            new ScoreInfo("Rubble", "Barney", 80),
            new ScoreInfo("Smith", "Judy", 48),
            new ScoreInfo("Dean", "James", 90),
            new ScoreInfo("Russ", "Joe", 55),
            new ScoreInfo("Wolfe", "Bill", 73),
            new ScoreInfo("Dart", "Mary", 54),
            new ScoreInfo("Rogers", "Chris", 78),
            new ScoreInfo("Toole", "Pat", 51),
            new ScoreInfo("Khan", "Omar", 93),
            new ScoreInfo("Smith", "Ann", 95)
    };

    public static void main(String[] args) {
        /* 1. 학생의 수를 구하시오. */
        System.out.println(Arrays.stream(scoreData).count());
        System.out.println();

        /* 2. 모든 학생의 평균 점수를 구하시오. */
        System.out.println(Arrays.stream(scoreData).mapToDouble(ScoreInfo::getScore).average().getAsDouble());
        System.out.println();

        /* 3. 90점 이상인 학생들을 출력하시오. */
        Arrays.stream(scoreData).filter(element -> element.getScore() >= 90)
                .map(ScoreInfo::getName).forEach(System.out::println);
        System.out.println();

        /* 4. 70점 미만인 학생의 이름이 포함된 List<String>을 만들고 해당 학생들을 출력하시오. */
        List<String> studentsWithPoorScore = Arrays.stream(scoreData)
                .filter(element -> element.getScore() < 70)
                .map(ScoreInfo::getName)
                .collect(Collectors.toList());
        for (String student : studentsWithPoorScore) {
            System.out.println(student);
        }
        System.out.println();

        /* 5. 성순으로 정렬하여 학생의 이름과 점수를 인쇄합니다. */
        Arrays.stream(scoreData).sorted(Comparator.comparing(ScoreInfo::getLastName)).forEach(System.out::println);
        System.out.println();

        /* 6. 점수순으로 정렬하여 학생의 이름과 점수를 인쇄합니다. */
        Arrays.stream(scoreData).sorted().forEach(System.out::println);
        System.out.println();

    }
}

class ScoreInfo implements Comparable<ScoreInfo> {
    private String lastName;
    private String firstName;
    private int score;

    public ScoreInfo(String lastName, String firstName, int score) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.score = score;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(getName()).append(" ").append(getScore()).toString();
    }

    @Override
    public int compareTo(ScoreInfo other) {
        return other.getScore() - this.getScore();
    }
}
