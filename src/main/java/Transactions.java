import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Transactions {
    private List<OperationType> operationsType;
    private List<Double> operations;
    private List<String> transactionsDate;
    private DateProvider dateProvider;

    private List<Transaction> transactions;

    public Transactions(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        operationsType = new ArrayList();
        operations = new ArrayList();
        transactionsDate = new ArrayList();

        transactions = new ArrayList();
    }


    public void addDeposit(double amount) {
        operationsType.add(OperationType.DEPOSIT);
        operations.add(amount);
        transactionsDate.add(dateProvider.todaysDateAsString());

        Transaction transaction = new Transaction(amount,OperationType.DEPOSIT,dateProvider.todaysDateAsString());
        transactions.add(transaction);
    }

    public void addWithdrawal(double amount) {
        operationsType.add(OperationType.WITHDRAWAL);
        operations.add(amount);
        transactionsDate.add(dateProvider.todaysDateAsString());

        Transaction transaction = new Transaction(amount,OperationType.WITHDRAWAL,dateProvider.todaysDateAsString());
        transactions.add(transaction);
    }

    public List<OperationType> getOperationsType() {
        return transactions.stream().map(t -> t.getOperationType()).collect(Collectors.toList());
    }

    public List<Double> getOperations() {
        return operations;
    }

    public List<String> getTransactionsDate() {
        return transactionsDate;
    }
}