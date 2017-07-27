import exceptions.IncorrectAmountException;
import exceptions.NotEnoughMoneyException;

import java.util.List;


public class BankAccount {
    private OperationsHistory operationsHistory;
    private OperationsHistoryPrinter operationsHistoryPrinter;

    public BankAccount(OperationsHistory operationsHistory, OperationsHistoryPrinter operationsHistoryPrinter) {
        this.operationsHistory = operationsHistory;
        this.operationsHistoryPrinter = operationsHistoryPrinter;
    }

    public double moneyStored() {
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
        if (moneyStored()<amount){
            throw new NotEnoughMoneyException();
        }
        operationsHistory.addWithdrawal(amount);
    }

    public List<OperationType> allOperationsType() {
        return operationsHistory.allOperationsType();
    }

    public List<Double> allOperationsAmount() {
        return operationsHistory.allTransactionsAmount();
    }

    public List<String> allOperationsDate() {
        return operationsHistory.allTransactionsDate();
    }

    public void printOperationsHistory() {
        operationsHistoryPrinter.print(operationsHistory);
    }
}
