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
        bankAccount.depositOf(2);

        assertThat(bankAccount.moneyStored()).isEqualTo(2);
    }

    @Test
    public void should_store_the_total_of_two_deposit() throws Exception {
        bankAccount.depositOf(2);
        bankAccount.depositOf(3.5);

        assertThat(bankAccount.moneyStored()).isEqualTo(5.5);
    }
}