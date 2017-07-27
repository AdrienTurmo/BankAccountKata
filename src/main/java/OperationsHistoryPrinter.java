import java.util.List;

public class OperationsHistoryPrinter {

    private static final String HEADER = "DATE | OPERATION | AMOUNT | BALANCE";
    private static final String LINE_TEMPLATE = "%s | %s | %s€ | %s€";
    private Printer printer;

    public OperationsHistoryPrinter(Printer printer) {
        this.printer = printer;
    }

    public void print(List<Operation> operations) {
        printer.print(HEADER);
        if (operations.size() > 0) {
            printLine(operations);
        }
    }

    private void printLine(List<Operation> operations) {
        Operation operation = operations.get(0);
        String line = String.format(LINE_TEMPLATE, operation.getDate(), operation.getOperationType(), operation.getAmount(), operation.getAmount());
        printer.print(line);
    }
}
