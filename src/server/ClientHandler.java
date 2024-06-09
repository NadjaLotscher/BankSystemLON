package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import commands.*;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientId;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Enter your ID:");
            clientId = in.readLine();
            System.out.println("Received client ID: " + clientId);

            // After receiving the client ID, prompt for action
            out.println("Choose action (1: Send Money, 2: Check Balance):");

            while (true) {
                String input = in.readLine();
                if (input == null) break;
                System.out.println("Received from client: " + input);
                handleInput(input);
            }
        } catch (Exception e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];

        System.out.println("Handling command: " + command);

        switch (command) {
            case "ACTION":
                handleAction(parts);
                break;
            case "SEND":
                handleSend(parts);
                break;
            case "REQUEST":
                handleRequest(parts);
                break;
            case "BALANCE":
                handleBalance();
                break;
            default:
                out.println("Unknown command: " + command);
                out.println("Choose action (1: Send Money, 2: Check Balance):");
        }
    }

    private void handleAction(String[] parts) {
        if (parts.length < 2) {
            out.println("Invalid ACTION command");
            out.println("Choose action (1: Send Money, 2: Check Balance):");
            return;
        }
        String action = parts[1];
        System.out.println("Action: " + action);
        switch (action) {
            case "1":
                out.println("Enter recipient ID:");
                break;
            case "2":
                handleBalance();
                break;
            default:
                out.println("Unknown ACTION: " + action);
                out.println("Choose action (1: Send Money, 2: Check Balance):");
        }
    }

    private void handleSend(String[] parts) {
        if (parts.length < 3) {
            out.println("Invalid SEND command");
            return;
        }
        String recipientId = parts[1];
        double amount = Double.parseDouble(parts[2]);
        System.out.println("Sending money: " + amount + " from " + clientId + " to " + recipientId);
        Command sendMoneyCommand = new SendMoneyCommand(amount, clientId, recipientId);
        sendMoneyCommand.execute();
        out.println("Money sent successfully.");
        out.println("Choose action (1: Send Money, 2: Check Balance):");
    }

    private void handleRequest(String[] parts) {
        if (parts.length < 3) {
            out.println("Invalid REQUEST command");
            return;
        }
        String senderId = parts[1];
        double amount = Double.parseDouble(parts[2]);
        // Implement similar to SendMoneyCommand for handling requests
        out.println("Request processed successfully.");
        out.println("Choose action (1: Send Money, 2: Check Balance):");
    }

    private void handleBalance() {
        System.out.println("Checking balance for: " + clientId);
        Command showBalanceCommand = new ShowBalanceCommand(clientId);
        double balance = showBalanceCommand.execute();
        out.println("Balance for account " + clientId + ": " + balance);
        out.println("Choose action (1: Send Money, 2: Check Balance):");
    }
}
