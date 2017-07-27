public class OperationsHistoryPrinter {

    private Printer printer;

    public OperationsHistoryPrinter(Printer printer) {
        this.printer = printer;
    }

    public void print(OperationsHistory operationsHistory) {
        printer.print("DATE  | OPERATION | AMOUNT | BALANCE");
    }
}
