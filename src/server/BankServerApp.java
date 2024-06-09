package server;

import java.net.ServerSocket;
import java.net.Socket;

public class BankServerApp {
    public static void main(String[] args) {

        System.out.println("Bank server is starting...");

        try {
            ServerSocket server = new ServerSocket(45000);
            System.out.println("Server is listening on port 45000");

            while (true) {
                Socket exchangeSocket = server.accept();
                System.out.println("Server accepted connection");

                // Start a new thread for each client connection
                new ClientHandler(exchangeSocket).start();
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not start server", e);
        }
    }
}