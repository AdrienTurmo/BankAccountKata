public class Transaction {
    private Double amount;
    private OperationType operationType;
    private String date;

    public Transaction(Double amount, OperationType operationType, String date) {
        this.amount = amount;
        this.operationType = operationType;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public String getDate() {
        return date;
    }
}
