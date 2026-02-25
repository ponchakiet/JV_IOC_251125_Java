package bt1;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static HashMap<Integer, Product> productMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Add product");
            System.out.println("2. Update product");
            System.out.println("3. Delete product");
            System.out.println("4. Show products");
            System.out.println("5. Filter price > 100");
            System.out.println("6. Calculate total price");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    showProducts();
                    break;
                case 5:
                    filterProducts();
                    break;
                case 6:
                    calculateTotal();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 3. Thêm sản phẩm
    public static void addProduct() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (productMap.containsKey(id)) {
            System.out.println("ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        Product product = new Product(id, name, price);
        productMap.put(id, product);

        System.out.println("Product added successfully!");
    }

    // 4. Sửa sản phẩm
    public static void updateProduct() {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!productMap.containsKey(id)) {
            System.out.println("Product not found!");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();

        Product product = productMap.get(id);
        product.setName(name);
        product.setPrice(price);

        System.out.println("Product updated successfully!");
    }

    // 5. Xóa sản phẩm
    public static void deleteProduct() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        if (productMap.remove(id) != null) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    // 6. Hiển thị sản phẩm
    public static void showProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products available!");
            return;
        }

        productMap.values().forEach(System.out::println);
    }

    // 7. Lọc sản phẩm price > 100
    public static void filterProducts() {
        List<Product> filtered = productMap.values()
                .stream()
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No products with price > 100");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    // 8. Tính tổng giá trị sản phẩm
    public static void calculateTotal() {
        double total = productMap.values()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Total price of all products: " + total);
    }
}