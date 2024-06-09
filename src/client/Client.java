package client;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 45000);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            LOGGER.info("Connected to the server!");

            // Initialize ClientService and start interaction
            ClientService clientService = new ClientService(scanner, writer, reader);
            clientService.startInteraction();

        } catch (Exception e) {
            LOGGER.severe("Failed to connect to the server: " + e.getMessage());
        }
    }
}