import java.util.ArrayList;
import java.util.List;

public class Transactions {
    List<OperationType> operationsType;
    List<Double> operations;

    public Transactions() {
        operationsType = new ArrayList();
        operations = new ArrayList();
    }


    public void addDeposit(double amount) {
        operationsType.add(OperationType.DEPOSIT);
        operations.add(amount);
    }

    public void addWithdrawal(double amount) {
        operationsType.add(OperationType.WITHDRAWAL);
        operations.add(amount);
    }

    public List<OperationType> getOperationsType() {
        return operationsType;
    }

    public List<Double> getOperations() {
        return operations;
    }
}