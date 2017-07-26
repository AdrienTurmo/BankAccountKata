import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountTest {

    private BankAccount bankAccount;

    @Mock
    AccountPrinter printer;

    @Before
    public void setUp() throws Exception {
        bankAccount = new BankAccount();
    }

    @Test
    public void should_create_a_new_bank_account_with_no_money() throws Exception {
        assertThat(bankAccount.moneyStored()).isEqualTo(0);
    }

    @Test
    public void should_store_a_deposit_with_a_positive_amount_of_money() throws Exception {
        bankAccount.deposit(2);

        assertThat(bankAccount.moneyStored()).isEqualTo(2);
    }

    @Test
    public void should_store_the_total_of_two_deposit() throws Exception {
        bankAccount.deposit(2);
        bankAccount.deposit(3.5);

        assertThat(bankAccount.moneyStored()).isEqualTo(5.5);
    }

    @Test
    public void should_withdraw_an_not_empty_account() throws Exception {
        bankAccount.deposit(5);

        bankAccount.withdraw(3.3);

        assertThat(bankAccount.moneyStored()).isEqualTo(1.7);
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
    public void should_store_each_transaction_individually() throws Exception {
        bankAccount.deposit(5);
        bankAccount.withdraw(2);

        List<Double> expectedOperations = Arrays.asList(5.0,2.0);

        assertThat(bankAccount.getOperations()).isEqualTo(expectedOperations);
    }

    @Test
    public void should_store_type_of_transaction() throws Exception {
        bankAccount.deposit(2);
        bankAccount.withdraw(1);
        bankAccount.deposit(3);

        List<OperationType> expectedOperationsType = Arrays.asList(OperationType.DEPOSIT,OperationType.WITHDRAWAL,OperationType.DEPOSIT);

        assertThat(bankAccount.getOperationsType()).isEqualTo(expectedOperationsType);
    }
}