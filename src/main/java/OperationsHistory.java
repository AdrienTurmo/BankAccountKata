import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OperationsHistory {
    private DateProvider dateProvider;
    private List<Operation> operations;

    public OperationsHistory(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        operations = new ArrayList();
    }


    public void addDeposit(double amount) {
        Operation transaction = new Operation(amount,OperationType.DEPOSIT,dateProvider.todaysDateAsString());
        operations.add(transaction);
    }

    public void addWithdrawal(double amount) {
        Operation transaction = new Operation(amount,OperationType.WITHDRAWAL,dateProvider.todaysDateAsString());
        operations.add(transaction);
    }

    public List<OperationType> allOperationsType() {
        return operations.stream()
                .map(Operation::getOperationType)
                .collect(Collectors.toList());
    }

    public List<Double> allTransactionsAmount() {
        return operations.stream()
                .map(Operation::getAmount)
                .collect(Collectors.toList());
    }

    public List<String> allTransactionsDate() {
        return operations.stream()
                .map(Operation::getDate)
                .collect(Collectors.toList());
    }

    public double balance() {
        return operations.stream()
                .map(t -> t.getOperationType() == OperationType.WITHDRAWAL ? -t.getAmount() : t.getAmount())
                .reduce((double) 0, (a, b) -> BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue());
    }

    public List<Operation> getOperations() {
        return operations;
    }
}