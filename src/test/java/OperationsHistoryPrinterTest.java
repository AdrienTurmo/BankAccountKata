import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationsHistoryPrinterTest {

    @Mock
    DateProvider dateProvider;
    @Mock
    Printer printer;

    private OperationsHistoryPrinter operationsHistoryPrinter;
    private OperationsHistory operationsHistory;

    @Before
    public void setUp() throws Exception {
        operationsHistoryPrinter = new OperationsHistoryPrinter(printer);
        operationsHistory = new OperationsHistory(dateProvider);
    }

    @Test
    public void historyPrinter_print_header() throws Exception {
        operationsHistoryPrinter.print(operationsHistory);

        verify(printer).print("DATE | OPERATION | AMOUNT | BALANCE");
    }

    @Test
    public void historyPrinter_print_transactions_from_newest_to_oldest() throws Exception {
        when(dateProvider.todaysDateAsString()).thenReturn("26-07-2017");
        operationsHistory.addDeposit(100);
        operationsHistory.addDeposit(900);
        when(dateProvider.todaysDateAsString()).thenReturn("27-07-2017");
        operationsHistory.addWithdrawal(500);

        operationsHistoryPrinter.print(operationsHistory);

        InOrder printerOrder = Mockito.inOrder(printer);
        printerOrder.verify(printer).print("DATE | OPERATION | AMOUNT | BALANCE");
        printerOrder.verify(printer).print("27-07-2017 | WITHDRAWAL | 500.0€ | 500.0€");
        printerOrder.verify(printer).print("26-07-2017 | DEPOSIT | 900.0€ | 1000.0€");
        printerOrder.verify(printer).print("26-07-2017 | DEPOSIT | 100.0€ | 100.0€");
    }

}