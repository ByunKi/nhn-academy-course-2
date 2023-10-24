package exercise.unit_3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise5_2 {

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("./exercise/resources/unit_3/sales_data.txt"))) {

            Map<Boolean, List<String>> rawDatas = lines
                    .collect(Collectors.partitioningBy(str -> str.contains("no report received")));

            List<String> invalidDatas = rawDatas.get(true);
            List<String> validDatas = rawDatas.get(false);

            System.out.println("Data가 존재하지 않는 도시들");
            invalidDatas.stream()
                    .forEach(element -> System.out.println(element.split(": ")[0]));

            System.out.print("Data가 존재하는 도시들의 Data 평균: ");
            double average = validDatas.stream()
                    .mapToDouble(data -> Double.parseDouble(data.split(": ")[1]))
                    .average()
                    .getAsDouble();

            System.out.println(average);

        } catch (IllegalStateException e) {
            System.out.println("수행된 Stream 연산을 재실행 할 수 없습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류가 발생하였습니다.");
        }
    }

}
