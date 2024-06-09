package server;

public class Transaction {
    private double amount;
    private String fromAccount;
    private String toAccount;
    private boolean successful;

    public Transaction(double amount, String fromAccount, String toAccount, boolean successful) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.successful = successful;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
