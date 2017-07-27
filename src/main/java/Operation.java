import java.util.Objects;

class Operation {
    private final Double amount;
    private final OperationType operationType;
    private final String date;

    Operation(Double amount, OperationType operationType, String date) {
        this.amount = amount;
        this.operationType = operationType;
        this.date = date;
    }

    Double getAmount() {
        return amount;
    }

    OperationType getOperationType() {
        return operationType;
    }

    String getDate() {
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
