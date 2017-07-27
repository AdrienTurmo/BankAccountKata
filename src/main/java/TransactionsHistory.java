import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TransactionsHistory {
    private DateProvider dateProvider;
    private List<Transaction> transactions;

    public TransactionsHistory(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        transactions = new ArrayList();
    }


    public void addDeposit(double amount) {
        Transaction transaction = new Transaction(amount,OperationType.DEPOSIT,dateProvider.todaysDateAsString());
        transactions.add(transaction);
    }

    public void addWithdrawal(double amount) {
        Transaction transaction = new Transaction(amount,OperationType.WITHDRAWAL,dateProvider.todaysDateAsString());
        transactions.add(transaction);
    }

    public List<OperationType> getOperationsType() {
        return transactions.stream()
                .map(Transaction::getOperationType)
                .collect(Collectors.toList());
    }

    public List<Double> getTransactionsAmount() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .collect(Collectors.toList());
    }

    public List<String> getTransactionsDate() {
        return transactions.stream()
                .map(Transaction::getDate)
                .collect(Collectors.toList());
    }

    public double balance() {
        return transactions.stream()
                .map(t -> t.getOperationType() == OperationType.WITHDRAWAL ? -t.getAmount() : t.getAmount())
                .reduce((double) 0, (a, b) -> BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue());
    }
}