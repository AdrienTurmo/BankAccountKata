import java.math.BigDecimal;
import java.util.List;


public class BankAccount {
    private Transactions transactions;

    public BankAccount(DateProvider dateProvider) {
        transactions = new Transactions(dateProvider);
    }

    public double moneyStored() {
        return transactions.balance();
    }

    public void deposit(double amount) throws IncorrectAmountException {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        transactions.addDeposit(amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        if (moneyStored()<amount){
            throw new NotEnoughMoneyException();
        }
        transactions.addWithdrawal(amount);
    }

    public List<OperationType> getOperationsType() {
        return transactions.getOperationsType();
    }


    public List<?> getTransactions() {
        return transactions.getTransactionsAmount();
    }

    public List<String> getTransactionsDate() {
        return transactions.getTransactionsDate();
    }
}
