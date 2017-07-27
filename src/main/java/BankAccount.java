import exceptions.IncorrectAmountException;
import exceptions.NotEnoughMoneyException;


public class BankAccount {
    private final OperationsHistory operationsHistory;
    private final OperationsHistoryPrinter operationsHistoryPrinter;

    public BankAccount(OperationsHistory operationsHistory, OperationsHistoryPrinter operationsHistoryPrinter) {
        this.operationsHistory = operationsHistory;
        this.operationsHistoryPrinter = operationsHistoryPrinter;
    }

    double balance() {
        return operationsHistory.balance();
    }

    public void deposit(double amount) throws IncorrectAmountException {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        operationsHistory.addDeposit(amount);
    }

    public void withdraw(double amount) throws Exception {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        if (balance()<amount){
            throw new NotEnoughMoneyException();
        }
        operationsHistory.addWithdrawal(amount);
    }

    public void printOperationsHistory() {
        operationsHistoryPrinter.print(operationsHistory);
    }
}
