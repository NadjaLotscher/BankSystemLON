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
        for (Transaction transaction : BankServer.getInstance().getTransactionHistory()) {
            if (transaction.getFromAccount().equals(accountId) || transaction.getToAccount().equals(accountId)) {
                history.add(transaction);
            }
        }

        System.out.println("Transaction history for account " + accountId + ":");
        for (Transaction transaction : history) {
            System.out.println(sdf.format(transaction.getTimestamp()) + " - "
                    + (transaction.isCredit() ? "Credit: " : "Debit: ")
                    + transaction.getAmount() + " from " + transaction.getFromAccount()
                    + " to " + transaction.getToAccount());
        }
        return 0;
    }
}
