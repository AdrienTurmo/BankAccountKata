import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OperationsHistoryTest {

    @Mock
    DateProvider dateProvider;

    private OperationsHistory operationsHistory;

    @Before
    public void setUp() throws Exception {
        operationsHistory = new OperationsHistory(dateProvider);
    }

    @Test
    public void should_add_a_deposit_with_correct_amount_and_date() throws Exception {
        Operation expectedOperation = new Operation(10.,OperationType.DEPOSIT,"26-07-2017");
        when(dateProvider.todaysDateAsString()).thenReturn("26-07-2017");

        operationsHistory.addDeposit(10.);

        assertThat(operationsHistory.getOperations().size()).isEqualTo(1);
        assertThat(operationsHistory.getOperations()).contains(expectedOperation);
    }

    @Test
    public void should_add_a_withdrawal_with_correct_amount_and_date() throws Exception {
        Operation expectedOperation = new Operation(100.,OperationType.WITHDRAWAL,"27-07-2017");
        when(dateProvider.todaysDateAsString()).thenReturn("27-07-2017");

        operationsHistory.addWithdrawal(100.);

        assertThat(operationsHistory.getOperations().size()).isEqualTo(1);
        assertThat(operationsHistory.getOperations()).contains(expectedOperation);
    }

    @Test
    public void should_calculate_the_balance() throws Exception {
        operationsHistory.addDeposit(100.);
        operationsHistory.addWithdrawal(50.);

        assertThat(operationsHistory.balance()).isEqualTo(50.);
    }
}