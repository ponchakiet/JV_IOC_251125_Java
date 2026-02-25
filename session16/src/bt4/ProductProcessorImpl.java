package bt4;

import java.util.List;

public class ProductProcessorImpl implements ProductProcessor {

    @Override
    public double calculateTotalValue(List<Product> products) {

        double total = 0;

        // dùng for-each
        for (Product p : products) {
            total += p.getPrice();
        }

        return total;

        // Nếu muốn dùng Stream:
        // return products.stream()
        //        .mapToDouble(Product::getPrice)
        //        .sum();
    }
}