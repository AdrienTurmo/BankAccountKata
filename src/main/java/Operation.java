import java.util.Objects;

public class Operation {
    private Double amount;
    private OperationType operationType;
    private String date;

    public Operation(Double amount, OperationType operationType, String date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(amount, operation.amount) &&
                operationType == operation.operationType &&
                Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, operationType, date);
    }
}
