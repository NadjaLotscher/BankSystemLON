package commands;

import server.BankServer;
import server.Transaction;

public class ShowBalanceCommand implements Command {
    private String account;
    private double balance;

    public ShowBalanceCommand(String account) {
        this.account = account;
    }

    // This method is responsible for executing the command (showing the balance of an account)
    @Override
    public double execute() {
        balance = 0.0;
        for (Transaction transaction : BankServer.getInstance().getTransactionHistory()) {
            if (transaction.isSuccessful()) {
                if (transaction.getToAccount().equals(account) && transaction.isCredit()) {
                    balance += transaction.getAmount();
                } else if (transaction.getFromAccount().equals(account) && !transaction.isCredit()) {
                    balance -= transaction.getAmount();
                }
            }
        }
        System.out.println("Balance for account " + account + ": " + balance);
        return balance;
    }
}
