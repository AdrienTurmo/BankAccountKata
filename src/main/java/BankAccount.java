import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal amount;

    public BankAccount() {
        amount = BigDecimal.valueOf(0);
    }

    public double moneyStored() {
        return amount.doubleValue();
    }

    public void deposit(double amount) {
        this.amount = this.amount.add(BigDecimal.valueOf(amount));
    }

    public void withdraw(double amount) {
        this.amount = this.amount.subtract(BigDecimal.valueOf(amount));
    }
}
