import exceptions.IncorrectAmountException;
import exceptions.NotEnoughMoneyException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountTest {

    private BankAccount bankAccount;

    @Mock
    DateProvider dateProvider;
    @Mock
    OperationsHistory operationsHistory;
    @Mock
    OperationsHistoryPrinter operationsHistoryPrinter;

    @Before
    public void setUp() throws Exception {
        bankAccount = new BankAccount(operationsHistory, operationsHistoryPrinter);
    }

    @Test
    public void should_create_a_new_bank_account_with_no_money() throws Exception {
        assertThat(bankAccount.balance()).isEqualTo(0);
    }

    @Test
    public void should_store_a_deposit_with_a_positive_amount_of_money() throws Exception {
        bankAccount.deposit(2);

        verify(operationsHistory).addDeposit(2);
    }

    @Test
    public void should_store_the_total_of_two_deposit() throws Exception {
        bankAccount.deposit(2);
        bankAccount.deposit(3.5);

        verify(operationsHistory).addDeposit(2);
        verify(operationsHistory).addDeposit(3.5);

    }

    @Test
    public void should_withdraw_an_not_empty_account() throws Exception {
        bankAccount.deposit(5);
        when(operationsHistory.balance()).thenReturn(5.);
        bankAccount.withdraw(3.3);

        verify(operationsHistory).addWithdrawal(3.3);
    }

    @Test(expected = IncorrectAmountException.class)
    public void should_not_be_able_to_deposit_a_negative_amount_of_money() throws Exception {
        bankAccount.deposit(-2);
    }


    @Test(expected = IncorrectAmountException.class)
    public void should_not_be_able_to_withdraw_a_negative_amount_of_money() throws Exception {
        bankAccount.withdraw(-2);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void should_not_be_able_to_withdraw_more_money_tha_money_stored() throws Exception {
        bankAccount.deposit(2);

        bankAccount.withdraw(3);
    }

    @Test
    public void historyPrinter_print_operationHistory() throws Exception {
        bankAccount.printOperationsHistory();

        verify(operationsHistoryPrinter).print(operationsHistory);
    }

}