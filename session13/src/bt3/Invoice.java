package bt3;

public class Invoice {
    private String code;
    private double amount;

    public Invoice(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Mã hóa đơn: " + code + ", Số tiền: " + amount;
    }
}
