import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OperationsHistory {
    private DateProvider dateProvider;
    private List<Operation> transactions;

    public OperationsHistory(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        transactions = new ArrayList();
    }


    public void addDeposit(double amount) {
        Operation transaction = new Operation(amount,OperationType.DEPOSIT,dateProvider.todaysDateAsString());
        transactions.add(transaction);
    }

    public void addWithdrawal(double amount) {
        Operation transaction = new Operation(amount,OperationType.WITHDRAWAL,dateProvider.todaysDateAsString());
        transactions.add(transaction);
    }

    public List<OperationType> allOperationsType() {
        return transactions.stream()
                .map(Operation::getOperationType)
                .collect(Collectors.toList());
    }

    public List<Double> allTransactionsAmount() {
        return transactions.stream()
                .map(Operation::getAmount)
                .collect(Collectors.toList());
    }

    public List<String> allTransactionsDate() {
        return transactions.stream()
                .map(Operation::getDate)
                .collect(Collectors.toList());
    }

    public double balance() {
        return transactions.stream()
                .map(t -> t.getOperationType() == OperationType.WITHDRAWAL ? -t.getAmount() : t.getAmount())
                .reduce((double) 0, (a, b) -> BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue());
    }
}