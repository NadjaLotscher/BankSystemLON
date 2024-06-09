package commands;

import server.BankServer;
import server.Transaction;

public class SendMoneyCommand implements Command {
    private double amount;
    private String fromAccount;
    private String toAccount;

    public SendMoneyCommand(double amount, String fromAccount, String toAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    @Override
    public double execute() {
        // Here we can add logic to check if the transaction is successful
        boolean successful = true; // Simplified for this example
        Transaction transaction = new Transaction(amount, fromAccount, toAccount, successful);
        BankServer.getInstance().addTransaction(transaction);
        System.out.println("Transaction completed: " + amount + " from " + fromAccount + " to " + toAccount);
        return 0;
    }
}
