import exceptions.IncorrectAmountException;
import exceptions.NotEnoughMoneyException;


class BankAccount {
    private final OperationsHistory operationsHistory;
    private final OperationsHistoryPrinter operationsHistoryPrinter;

    BankAccount(OperationsHistory operationsHistory, OperationsHistoryPrinter operationsHistoryPrinter) {
        this.operationsHistory = operationsHistory;
        this.operationsHistoryPrinter = operationsHistoryPrinter;
    }

    double balance() {
        return operationsHistory.balance();
    }

    void deposit(double amount) throws IncorrectAmountException {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        operationsHistory.addDeposit(amount);
    }

    void withdraw(double amount) throws IncorrectAmountException,NotEnoughMoneyException {
        if (amount < 0) {
            throw new IncorrectAmountException();
        }
        if (balance()<amount){
            throw new NotEnoughMoneyException();
        }
        operationsHistory.addWithdrawal(amount);
    }

    void printOperationsHistory() {
        operationsHistoryPrinter.print(operationsHistory);
    }
}
