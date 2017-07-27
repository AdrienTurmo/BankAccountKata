import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class OperationsHistoryPrinter {

    private static final String HEADER = "DATE | OPERATION | AMOUNT | BALANCE";
    private static final String LINE_TEMPLATE = "%s | %s | %s€ | %s€";
    private final Printer printer;

    public OperationsHistoryPrinter(Printer printer) {
        this.printer = printer;
    }

    public void print(OperationsHistory operationsHistory) {
        BigDecimal[] balance = {BigDecimal.valueOf(operationsHistory.balance())};
        List<Operation> operations = operationsHistory.getOperations();

        printer.print(HEADER);
        Collections.reverse(operations);
        operations.forEach(operation -> balance[0] = printLine(operation, balance[0]));
    }

    private BigDecimal printLine(Operation operation, BigDecimal balance) {
        Double operationAmount = operation.getAmount();
        OperationType operationType = operation.getOperationType();

        String line = String.format(LINE_TEMPLATE,
                operation.getDate(),
                operationType,
                operationAmount,
                balance.doubleValue());
        printer.print(line);
        return balance.subtract(BigDecimal.valueOf(operationType == OperationType.DEPOSIT ? operationAmount : -operationAmount));
    }
}
