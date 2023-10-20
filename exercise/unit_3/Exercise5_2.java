package exercise.unit_3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise5_2 {

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Path.of("./exercise/resources/unit_3/sales_data.txt"))) {

            List<String> datas = lines.map(str -> str.split(": "))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());

            for (String data : datas) {
                System.out.println(data);
            }

        } catch (IllegalStateException e) {
            System.out.println("수행된 Stream 연산을 재실행 할 수 없습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류가 발생하였습니다.");
        }
    }

}
