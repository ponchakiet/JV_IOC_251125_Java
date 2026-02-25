package bt4;

import java.util.List;
import java.util.function.Predicate;

public interface ProductProcessor {

    // Abstract method
    double calculateTotalValue(List<Product> products);

    // Static method
    static void printProductList(List<Product> products) {
        System.out.println("=== Product List ===");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    // Default method
    default boolean hasExpensiveProduct(List<Product> products) {

        Predicate<Product> expensivePredicate =
                product -> product.getPrice() > 100;

        return products.stream().anyMatch(expensivePredicate);
    }
}
