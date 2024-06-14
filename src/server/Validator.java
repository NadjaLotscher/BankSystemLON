package server;

import java.util.LinkedList;

public class Validator {

    // Validate that the account exists
    public static boolean validateAccountExists(String accountId) {
        // Logic to check if account exists in the system
        // For now, we will assume all accounts are valid
        return true;
    }

    // Validate that the amount is positive
    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    // Validate sufficient balance for transaction
    public static boolean validateSufficientBalance(String fromAccount, double amount) {
        double balance = 0.0;
        LinkedList<Transaction> transactionHistory = BankServer.getInstance().getTransactionHistory();
        for (Transaction transaction : transactionHistory) {
            if (transaction.isSuccessful()) {
                if (transaction.getToAccount().equals(fromAccount) && transaction.isCredit()) {
                    balance += transaction.getAmount();
                } else if (transaction.getFromAccount().equals(fromAccount) && !transaction.isCredit()) {
                    balance -= transaction.getAmount();
                }
            }
        }
        return balance >= amount;
    }
}
