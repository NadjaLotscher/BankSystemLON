package server;

import java.util.LinkedList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BankServer {
    private static BankServer instance;
    private LinkedList<Transaction> transactionHistory;

    private BankServer() {
        transactionHistory = new LinkedList<>();
        addTestTransactions();
    }

    // Singleton pattern
    public static synchronized BankServer getInstance() {
        if (instance == null) {
            instance = new BankServer();
        }
        return instance;
    }

    // Transaction history
    public LinkedList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    // Add test transactions
    private void addTestTransactions() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Add test transactions with parsed dates
            Transaction t1 = new Transaction(100, "1", "2", true, false, sdf.parse("2023-06-10"));
            Transaction t2 = new Transaction(200, "2", "1", true, true, sdf.parse("2023-06-11"));
            Transaction t3 = new Transaction(300, "1", "3", true, false, sdf.parse("2023-06-12"));
            Transaction t4 = new Transaction(400, "3", "1", true, true, sdf.parse("2023-06-13"));

            transactionHistory.add(t1);
            transactionHistory.add(t2);
            transactionHistory.add(t3);
            transactionHistory.add(t4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
