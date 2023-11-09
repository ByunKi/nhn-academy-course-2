package exercise.unit_11;

import java.net.*;
import java.io.*;

public class Exercise4 {
    public static void main(String[] args) {

        String computer; // Name or IP address of server.
        Socket connection; // Socket for communicating with that computer.
        PrintWriter outgoing; // Stream for sending a command to the server.
        BufferedReader incoming; // Stream for reading data from the connection.
        String command; // Command to send to the server.

        /*
         * Check that the number of command-line arguments is legal.
         * If not, print a usage message and end.
         */

        if (args.length == 0 || args.length > 3) {
            System.out.println("Usage:  java FileClient <server>");
            System.out.println("    or  java FileClient <server> <file>");
            System.out.println(
                    "    or  java FileClient <server> <file> <local-file>");
            return;
        }

        /* Get the server name and the message to send to the server. */

        computer = args[0];

        if (args.length == 1)
            command = "INDEX";
        else
            command = "GET " + args[1];

        /*
         * Make the connection and open streams for communication.
         * Send the command to the server. If something fails
         * during this process, print an error message and end.
         */

        try {
            connection = new Socket(computer, Exercise3.DEFAULT_PORT);
            incoming = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            outgoing = new PrintWriter(connection.getOutputStream());
            outgoing.println(command);
            outgoing.flush(); // ESSENTIAL: Make sure command is dispatched to server!
        } catch (Exception e) {
            System.out.println(
                    "Can't make connection to server at \"" + args[0] + "\".");
            System.out.println("Error:  " + e);
            return;
        }

        /* Read and process the server's response to the command. */

        try {
            if (args.length == 1) {
                // The command was "index". Read and display lines
                // from the server until the end-of-stream is reached.
                System.out.println("File list from server:");
                while (true) {
                    String line = incoming.readLine();
                    if (line == null)
                        break;
                    System.out.println("   " + line);
                }
            } else {
                // The command was "get <file-name>". Read the server's
                // response message. If the message is "OK", get the file.
                String message = incoming.readLine();
                if (!message.equalsIgnoreCase("OK")) {
                    System.out.println("File not found on server.");
                    System.out.println("Message from server: " + message);
                    return;
                }
                PrintWriter fileOut; // For writing the received data to a file.
                if (args.length == 3) {
                    // Use the third parameter as a file name.
                    // This will overwrite a file with the same name!
                    fileOut = new PrintWriter(new FileWriter(args[2]));
                } else {
                    // Use the second parameter as a file name,
                    // but don't replace an existing file.
                    File file = new File(args[1]);
                    if (file.exists()) {
                        System.out.println("A file with that name already exists.");
                        System.out.println("To replace it, use the three-argument");
                        System.out.println("version of the command.");
                        return;
                    }
                    fileOut = new PrintWriter(new FileWriter(args[1]));
                }
                while (true) {
                    // Copy lines from incoming to the file until
                    // the end of the incoming stream is encountered.
                    String line = incoming.readLine();
                    if (line == null)
                        break;
                    fileOut.println(line);
                }
                if (fileOut.checkError()) {
                    System.out.println("Some error occurred while writing the file.");
                    System.out.println("Output file might be empty or incomplete.");
                } else {
                    System.out.println("File has been received.");
                }
            }
        } catch (Exception e) {
            System.out.println("Sorry, an error occurred while reading data from the server.");
            System.out.println("Error: " + e);
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
            }
        }

    }

}