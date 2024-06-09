package server;

import java.util.LinkedList;

public class BankServer {
    private static BankServer instance;
    private LinkedList<Transaction> transactionHistory;

    private BankServer() {
        transactionHistory = new LinkedList<>();
    }

    public static synchronized BankServer getInstance() {
        if (instance == null) {
            instance = new BankServer();
        }
        return instance;
    }

    public LinkedList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
}
