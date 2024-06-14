package commands;

import server.BankServer;
import server.Transaction;

import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class TransactionHistoryCommand implements Command {
    private String accountId;

    public TransactionHistoryCommand(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public double execute() {
        List<Transaction> history = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder historyString = new StringBuilder();

        for (Transaction transaction : BankServer.getInstance().getTransactionHistory()) {
            if (transaction.getFromAccount().equals(accountId) || transaction.getToAccount().equals(accountId)) {
                history.add(transaction);
            }
        }

        historyString.append("Transaction history for account ").append(accountId).append(":\n");
        for (Transaction transaction : history) {
            historyString.append(sdf.format(transaction.getTimestamp())).append(" - ")
                    .append(transaction.isCredit() ? "Credit: " : "Debit: ")
                    .append(transaction.getAmount()).append(" from ").append(transaction.getFromAccount())
                    .append(" to ").append(transaction.getToAccount()).append("\n");
        }

        System.out.println(historyString.toString());
        return 0;
    }

    public String getHistory() {
        List<Transaction> history = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder historyString = new StringBuilder();

        for (Transaction transaction : BankServer.getInstance().getTransactionHistory()) {
            if (transaction.getFromAccount().equals(accountId) || transaction.getToAccount().equals(accountId)) {
                history.add(transaction);
            }
        }

        historyString.append("Transaction history for account ").append(accountId).append(":\n");
        for (Transaction transaction : history) {
            historyString.append(sdf.format(transaction.getTimestamp())).append(" - ")
                    .append(transaction.isCredit() ? "Credit: " : "Debit: ")
                    .append(transaction.getAmount()).append(" from ").append(transaction.getFromAccount())
                    .append(" to ").append(transaction.getToAccount()).append("\n");
        }

        return historyString.toString();
    }
}
