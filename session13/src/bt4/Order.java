package bt4;

public class Order {
    private String orderId;
    private String customerName;

    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Mã đơn hàng: " + orderId + ", Khách hàng: " + customerName;
    }
}
