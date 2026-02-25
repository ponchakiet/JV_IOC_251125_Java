package bt4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Tạo danh sách sản phẩm
        List<Product> products = new ArrayList<>();

        products.add(new Product("Laptop", 1500));
        products.add(new Product("Mouse", 50));
        products.add(new Product("Keyboard", 120));
        products.add(new Product("Book", 30));

        ProductProcessor processor = new ProductProcessorImpl();

        // Kiểm tra có sản phẩm > 100
        if (processor.hasExpensiveProduct(products)) {
            System.out.println("Co san pham dat tien (>100)");
        } else {
            System.out.println("Khong co san pham dat tien");
        }

        // Tính tổng giá trị
        double total = processor.calculateTotalValue(products);
        System.out.println("Tong gia tri san pham: " + total);

        // In danh sách bằng static method
        ProductProcessor.printProductList(products);
    }
}
