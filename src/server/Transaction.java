package server;

import java.util.Date;

public class Transaction {
    private double amount;
    private String fromAccount;
    private String toAccount;
    private boolean successful;
    private boolean isCredit;
    private Date timestamp;

    public Transaction(double amount, String fromAccount, String toAccount, boolean successful, boolean isCredit, Date timestamp) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.successful = successful;
        this.isCredit = isCredit;
        if (timestamp != null) {
            this.timestamp = timestamp;
        } else {
            this.timestamp = new Date(); // Set the current date and time
        }
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

    public boolean isCredit() {
        return isCredit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
