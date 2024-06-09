package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientService {
    private final Scanner scanner;
    private final PrintWriter out;
    private final BufferedReader buffin;

    public ClientService(Scanner scanner, PrintWriter out, BufferedReader buffin) {
        this.scanner = scanner;
        this.out = out;
        this.buffin = buffin;
    }

    public void startInteraction() throws IOException {
        System.out.println(buffin.readLine());  // Initial prompt from the server to enter ID

        // Send client ID to the server
        String clientId = scanner.nextLine();
        out.println(clientId);

        while (true) {
            String serverResponse = buffin.readLine();
            System.out.println("Server: " + serverResponse);

            if (serverResponse.contains("Enter recipient ID:")) {
                // Do not print the prompt again
                String recipientId = scanner.nextLine();
                out.println("SEND " + recipientId + " " + getAmount());
            } else if (serverResponse.contains("Enter your ID:")) {
                // Do not print the prompt again
                String id = scanner.nextLine();
                out.println("ID " + id);
            } else if (serverResponse.contains("Choose action")) {
                // Do not print the prompt again
                String action = scanner.nextLine();
                out.println("ACTION " + action);
            } else if (serverResponse.contains("Money sent successfully.")) {
                // No need to print "Transaction complete."
            } else if (serverResponse.contains("Balance for account")) {
                // Do not print the prompt again
            }
        }
    }

    private String getAmount() {
        System.out.print("Enter amount: ");
        return scanner.nextLine();
    }
}
