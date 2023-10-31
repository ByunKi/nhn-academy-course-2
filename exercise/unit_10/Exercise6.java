package exercise.unit_10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Exercise6 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("./exercise/resources/unit_10/text.txt")) {
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[1024];
            while ((reader.read(buffer)) != -1) {
                builder.append(buffer);
            }

            String[] rawData = builder.toString().split("\\\n");
            String[][] words = new String[rawData.length][];
            for (int i = 0; i < words.length; i++) {
                words[i] = rawData[i].replaceAll("[,\\.\\'\\\"0-9]", "").split(" ");
            }

            Map<String, List<Index>> map = getConcordence(words);
            for (String key : map.keySet()) {
                System.out.println(key);
                for (Index element : map.get(key)) {
                    System.out.println(element);
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            System.out.println("입출력 오류가 발생하였습니다.");
        }
    }

    @SuppressWarnings(value = { "Key: String, Value: List<Index>" })
    private static <K, V> Map<K, V> getConcordence(String[][] data) {
        Map<String, List<Index>> concordence = initMap(data);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j].length() <= 3) {
                    continue;
                }
                concordence.get(data[i][j].trim()).add(new Index((i + 1), concatString(data[i]).indexOf(data[i][j])));
            }
        }

        return (Map<K, V>) concordence;
    }

    @SuppressWarnings(value = { "Key: String, Value: List<Index>" })
    private static <K, V> Map<K, V> initMap(String[][] data) {
        TreeMap<String, List<Index>> result = new TreeMap<>();
        for (String[] words : data) {
            for (String word : words) {
                if (word.length() <= 3) {
                    continue;
                }
                result.putIfAbsent(word.trim(), new ArrayList<>());
            }
        }

        return (Map<K, V>) result;
    }

    private static String concatString(String[] words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word);
        }

        return builder.toString();
    }
}

class Index {
    private int lineNumber;
    private int lineIndex;

    public Index(int lineNumber, int lineIndex) {
        this.lineNumber = lineNumber;
        this.lineIndex = lineIndex;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    @Override
    public String toString() {
        return "lineNumber: " + lineNumber + ", lineIndex: " + lineIndex;
    }

}