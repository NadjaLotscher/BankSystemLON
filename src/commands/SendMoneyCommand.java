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
        boolean successful = true; // Simplified for this project

        // Add debit transaction for the sender
        Transaction debitTransaction = new Transaction(amount, fromAccount, toAccount, successful, false);
        BankServer.getInstance().addTransaction(debitTransaction);

        // Add credit transaction for the recipient
        Transaction creditTransaction = new Transaction(amount, fromAccount, toAccount, successful, true);
        BankServer.getInstance().addTransaction(creditTransaction);

        System.out.println("Transaction completed: " + amount + " from " + fromAccount + " to " + toAccount);
        return 0;
    }
}
