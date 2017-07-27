import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class OperationsHistory {
    private final DateProvider dateProvider;
    private final List<Operation> operations;

    OperationsHistory(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
        operations = new ArrayList<>();
    }


    void addDeposit(double amount) {
        Operation transaction = new Operation(amount,OperationType.DEPOSIT,dateProvider.todaysDateAsString());
        operations.add(transaction);
    }

    void addWithdrawal(double amount) {
        Operation transaction = new Operation(amount,OperationType.WITHDRAWAL,dateProvider.todaysDateAsString());
        operations.add(transaction);
    }

    double balance() {
        return operations.stream()
                .map(t -> t.getOperationType() == OperationType.WITHDRAWAL ? -t.getAmount() : t.getAmount())
                .reduce((double) 0, (a, b) -> BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue());
    }

    List<Operation> getOperationsFromNewestToOldest() {
        List<Operation> revertedOperations = new ArrayList<>();
        operations.forEach(operation -> revertedOperations.add(operation));
        Collections.reverse(revertedOperations);
        return revertedOperations;
    }

}