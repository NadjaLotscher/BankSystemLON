package commands;

import server.BankServer;
import server.Transaction;
import server.Validator;

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
        // Perform validation
        if (!Validator.validateAccountExists(fromAccount) || !Validator.validateAccountExists(toAccount)) {
            System.out.println("Invalid account ID.");
            return -1;
        }

        if (!Validator.validateAmount(amount)) {
            System.out.println("Invalid amount. Amount must be greater than zero.");
            return -1;
        }

        if (!Validator.validateSufficientBalance(fromAccount, amount)) {
            System.out.println("Insufficient balance.");
            return -1;
        }

        // Proceed with the transaction if all validations pass
        boolean successful = true;

        // Add debit transaction for the sender
        Transaction debitTransaction = new Transaction(amount, fromAccount, toAccount, successful, false, null);
        BankServer.getInstance().addTransaction(debitTransaction);

        // Add credit transaction for the recipient
        Transaction creditTransaction = new Transaction(amount, fromAccount, toAccount, successful, true, null);
        BankServer.getInstance().addTransaction(creditTransaction);

        System.out.println("Transaction completed: " + amount + " from " + fromAccount + " to " + toAccount);
        return 0;
    }
}
