import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {

    private BankAccount bankAccount;

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

}