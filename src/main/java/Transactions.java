import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Transactions {
    private DateProvider dateProvider;

    private List<Transaction> transactions;

    public Transactions(DateProvider dateProvider) {
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
                .map(t -> t.getOperationType())
                .collect(Collectors.toList());
    }

    public List<Double> getOperations() {
        return transactions.stream()
                .map(t -> t.getAmount())
                .collect(Collectors.toList());
    }

    public List<String> getTransactionsDate() {
        return transactions.stream()
                .map(t -> t.getDate())
                .collect(Collectors.toList());
    }
}