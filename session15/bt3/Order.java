package bt3;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products; // List<Product>

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new ArrayList<>(); // có thể đổi LinkedList nếu muốn
    }

    public int getOrderId() { return orderId; }
    public List<Product> getProducts() { return products; }

    public void addProduct(Product p) {
        products.add(p);
    }

    public double totalAmount() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", items=" + products.size() +
                ", total=" + String.format("%.2f", totalAmount()) + "}";
    }
}
