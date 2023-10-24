package exercise.unit_7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise6 {
    public static void main(String[] args) {
        try {
            Path readingPath = Paths.get("./exercise/resources/unit_7/random_texts.txt");
            Path writingPath = Paths.get("./exercise/resources/unit_7/test_result.txt");

            List<String> rawDatas = Files.readAllLines(readingPath);
            List<String> datas = rawDatas
                    .stream()
                    .distinct()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            Collections.sort(datas);
            Files.write(writingPath, datas);

        } catch (IllegalStateException e) {
            System.out.println("수행된 Stream 연산을 재실행 할 수 없습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류가 발생하였습니다.");
        }
    }
}