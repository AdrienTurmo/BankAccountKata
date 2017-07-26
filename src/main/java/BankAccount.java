import java.math.BigDecimal;
import java.util.List;


public class BankAccount {
    private final Transactions transactions = new Transactions();
    private BigDecimal amount;

    public BankAccount() {
        amount = BigDecimal.valueOf(0);
    }

    public double moneyStored() {
        return amount.doubleValue();
    }

    public void deposit(double amount) throws IncorrectAmountException {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        this.amount = this.amount.add(BigDecimal.valueOf(amount));

        transactions.addDeposit(amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        if (this.amount.compareTo(BigDecimal.valueOf(amount))<0){
            throw new NotEnoughMoneyException();
        }
        this.amount = this.amount.subtract(BigDecimal.valueOf(amount));
        transactions.addWithdrawal(amount);
    }

    public List<OperationType> getOperationsType() {
        return transactions.getOperationsType();
    }


    public List<?> getTransactions() {
        return transactions.getOperations();
    }
}
