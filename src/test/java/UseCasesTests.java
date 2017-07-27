import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseCasesTests {

    private BankAccount bankAccount;

    @Mock
    private
    Printer printer;
    @Mock
    private
    DateProvider dateProvider;

    @Before
    public void setUp() throws Exception {
        OperationsHistory operationsHistory = new OperationsHistory(dateProvider);
        OperationsHistoryPrinter operationsHistoryPrinter = new OperationsHistoryPrinter(printer);
        bankAccount = new BankAccount(operationsHistory, operationsHistoryPrinter);
    }

    @Test
    public void can_make_deposits_into_an_account() throws Exception {
        when(dateProvider.todaysDateAsString()).thenReturn("27-06-2017");
        bankAccount.deposit(500);
        bankAccount.deposit(1500);
        bankAccount.deposit(0.50);

        assertThat(bankAccount.balance()).isEqualTo(2000.50);
    }

    @Test
    public void can_make_withdrawals_from_an_account() throws Exception {
        when(dateProvider.todaysDateAsString()).thenReturn("27-06-2017");
        bankAccount.deposit(1000);
        bankAccount.withdraw(500);
        bankAccount.withdraw(0.50);

        assertThat(bankAccount.balance()).isEqualTo(499.50);
    }

    @Test
    public void can_print_operations_history_of_an_account() throws Exception {
        when(dateProvider.todaysDateAsString()).thenReturn("01-06-2017");
        bankAccount.deposit(1000);

        when(dateProvider.todaysDateAsString()).thenReturn("26-07-2017");
        bankAccount.withdraw(500);

        when(dateProvider.todaysDateAsString()).thenReturn("26-07-2017");
        bankAccount.withdraw(200);

        when(dateProvider.todaysDateAsString()).thenReturn("27-07-2017");
        bankAccount.deposit(600);

        bankAccount.printOperationsHistory();

        InOrder printerOrder = Mockito.inOrder(printer);
        printerOrder.verify(printer).print("DATE | OPERATION | AMOUNT | BALANCE");
        printerOrder.verify(printer).print("27-07-2017 | DEPOSIT | 600.0€ | 900.0€");
        printerOrder.verify(printer).print("26-07-2017 | WITHDRAWAL | 200.0€ | 300.0€");
        printerOrder.verify(printer).print("26-07-2017 | WITHDRAWAL | 500.0€ | 500.0€");
        printerOrder.verify(printer).print("01-06-2017 | DEPOSIT | 1000.0€ | 1000.0€");
    }

}