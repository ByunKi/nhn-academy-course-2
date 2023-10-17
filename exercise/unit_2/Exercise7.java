package exercise.unit_2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exercise7 {
    public static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("./exercise/resources/unit_2/testdata.txt")) {
            StringBuilder builder = new StringBuilder();

            int read = -1;
            char[] buffer = new char[BUFFER_SIZE];
            while ((read = reader.read(buffer)) != -1) {
                builder.append(buffer);
            }

            String[] array = builder.toString().split("\n");
            String name = array[0];

            int result = 0;
            int count = array.length - 2;
            for (int i = 1; i < array.length - 1; i++) {
                result += Integer.parseInt(array[i]);
            }

            System.out.println(name + " " + (result / count));
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");

        } catch (IOException e) {
            System.out.println("IO 예외 발생");
        }
    }
}
