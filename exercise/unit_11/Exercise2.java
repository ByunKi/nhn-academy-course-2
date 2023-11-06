package exercise.unit_11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exercise2 {

    static final String PATH = "./exercise/resources/unit_11";

    public static void main(String[] args) {
        long result = 0;
        if (args.length > 0) {
            for (String arg : args) {
                result += lineCounts(PATH + "/" + arg);
            }
        } else {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("File을 입력하세요, 공백 문자로 구분됩니다. ");
                String[] files = scanner.nextLine().split(" ");
                for (String file : files) {
                    if (!file.contains(".txt")) {
                        System.out.println("적절한 파일의 형식이 아닙니다.");
                        throw new IllegalArgumentException();
                    }
                    result += lineCounts(PATH + "/" + file);
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(result);
    }

    private static long lineCounts(String fileName) {
        long count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류 발생");
        }

        return count;
    }
}