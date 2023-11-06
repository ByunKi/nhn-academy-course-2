package exercise.unit_11;

import java.io.File;
import java.io.FileNotFoundException;

public class Exercise1 {

    private static final String PATH = "./exercise";

    public static void main(String[] args) {
        try {
            printDirectory(0, new File(PATH));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printDirectory(int indents, File name) throws FileNotFoundException {
        if (!name.exists()) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다.");
        }

        String blankString = getIndent(indents);
        File[] files = name.listFiles();
        for (File file : files) {
            System.out.println(blankString + file.getName());
            if (file.isDirectory()) {
                printDirectory(indents + 1, file);
            }
        }
    }

    private static String getIndent(int number) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number; i++) {
            builder.append("    ");
        }
        return builder.toString();
    }
}
