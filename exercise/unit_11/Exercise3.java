package exercise.unit_11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Exercise3 {

    static final int DEFAULT_PORT = 43984;

    public static void main(String[] args) {
        String path = args.length > 0 ? args[0] : Exercise2.PATH;
        File file = new File(path);
        if (!file.exists()) {
            System.out.println();
            return;
        }

        if (!file.isDirectory()) {
            System.out.println();
            return;
        }

        try (ServerSocket listener = new ServerSocket()) {
            Socket connection = listener.accept();
            handleConnection(file, connection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleConnection(File file, Socket connection) {
        try (Scanner input = new Scanner(connection.getInputStream());
                PrintWriter output = new PrintWriter(connection.getOutputStream())) {
            String command = input.nextLine();
            if (command.equalsIgnoreCase("index")) {
                sendIndex(file, output);
            } else if (command.toLowerCase().startsWith("get")) {
                String fileName = command.substring(3);
                sendFile(fileName, file, output);

            } else {
                System.out.println("Error");
                output.flush();
            }
            System.out.println("OK");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private static void sendIndex(File file, PrintWriter output) throws IOException {
        String[] fileNames = file.list();
        for (String fileName : fileNames) {
            output.println(fileName);
        }
        output.flush();
        output.close();

        if (output.checkError()) {
            throw new IOException();
        }
    }

    private static void sendFile(String fileName, File file, PrintWriter output) {
        File designated = new File(file, fileName);
        if (!designated.exists() || designated.isDirectory()) {
            output.println("ERROR");
        } else {
            output.println("OK");
            try (BufferedReader reader = new BufferedReader(new FileReader(designated))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    output.println(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("파일을 찾을 수 없습니다.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
