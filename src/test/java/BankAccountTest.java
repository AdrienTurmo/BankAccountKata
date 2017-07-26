import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {


    @Test
    public void should_create_a_new_bank_account_with_no_money() throws Exception {
        BankAccount bankAccount = new BankAccount();

        assertThat(bankAccount.moneyStored()).isEqualTo(0);
    }

}