package exercise.unit_3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Exercise5 {

    public static void main(String[] args) {
        int bufferSize = 1024;
        int count = 0;
        double result = 0d;
        List<String> inavailableCites = new ArrayList<>();

        try (FileReader reader = new FileReader("./exercise/resources/unit_3/sales_data.txt")) {
            int read = -1;
            char[] buffer = new char[bufferSize];
            StringBuilder builder = new StringBuilder();

            while ((read = reader.read(buffer)) != -1) {
                builder.append(buffer);
            }

            String[] cityAndSales = builder.toString().split("\\\n");
            for (int i = 0; i < cityAndSales.length - 1; i++) {

                String[] information = cityAndSales[i].split(": ");
                if (information[1].equals("no report received")) {
                    inavailableCites.add(information[0]);
                } else {
                    result += Double.parseDouble(information[1]);
                    count++;
                }
            }

            System.out.println(count == 0 ? 0 : result / count);
        } catch (FileNotFoundException e) {
            System.out.println("파일이 존재하지 않습니다.");
        } catch (IOException e) {
            System.out.println("I/O 오류가 발생하였습니다.");
        }
    }

    public static <T> void printCites(List<T> list) {
        Iterator<T> cities = list.iterator();
        while (cities.hasNext()) {
            System.out.println(cities.next());
        }
    }
}
