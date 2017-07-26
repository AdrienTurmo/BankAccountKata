import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BankAccount {
    private BigDecimal amount;
    private List<OperationType> operationsType;
    private List<Double> operations;

    public BankAccount() {
        amount = BigDecimal.valueOf(0);
        operationsType = new ArrayList();
        operations = new ArrayList();
    }

    public double moneyStored() {
        return amount.doubleValue();
    }

    public void deposit(double amount) throws IncorrectAmountException {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        this.amount = this.amount.add(BigDecimal.valueOf(amount));
        operationsType.add(OperationType.DEPOSIT);
        operations.add(amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        if (this.amount.compareTo(BigDecimal.valueOf(amount))<0){
            throw new NotEnoughMoneyException();
        }
        this.amount = this.amount.subtract(BigDecimal.valueOf(amount));
        operationsType.add(OperationType.WITHDRAWAL);
        operations.add(amount);
    }

    public List<OperationType> getOperationsType() {
        return operationsType;
    }


    public List<?> getOperations() {
        return operations;
    }
}
