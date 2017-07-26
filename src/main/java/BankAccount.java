public class BankAccount {
    private int amount;

    public BankAccount() {
        amount = 0;
    }

    public int moneyStored() {
        return amount;
    }

    public void depositOf(int amount) {
        this.amount = amount;
    }
}
