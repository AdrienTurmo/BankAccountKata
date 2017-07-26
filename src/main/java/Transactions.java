import java.util.ArrayList;
import java.util.List;

public class Transactions {
    List<OperationType> operationsType;
    List<Double> operations;
    private List<String> transactionsDate;
    private DateProvider dateProvider;

    public Transactions(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        operationsType = new ArrayList();
        operations = new ArrayList();
        transactionsDate = new ArrayList();
    }


    public void addDeposit(double amount) {
        operationsType.add(OperationType.DEPOSIT);
        operations.add(amount);
        transactionsDate.add(dateProvider.todaysDateAsString());
    }

    public void addWithdrawal(double amount) {
        operationsType.add(OperationType.WITHDRAWAL);
        operations.add(amount);
        transactionsDate.add(dateProvider.todaysDateAsString());
    }

    public List<OperationType> getOperationsType() {
        return operationsType;
    }

    public List<Double> getOperations() {
        return operations;
    }

    public List<String> getTransactionsDate() {
        return transactionsDate;
    }
}