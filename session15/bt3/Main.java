package bt3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static final ProductManager<Product> productManager = new ProductManager<>();

    // Map<String, Order> theo yêu cầu
    // Bạn có thể đổi giữa 3 loại để thấy khác nhau:
    // HashMap: nhanh, không đảm bảo thứ tự
    // LinkedHashMap: giữ thứ tự thêm vào
    // TreeMap: tự sắp xếp theo key
    private static final Map<String, Order> orders = new LinkedHashMap<>();

    public static void main(String[] args) {
        seedData();

        while (true) {
            printMenu();
            int choice = readInt("Chọn: ");

            try {
                switch (choice) {
                    case 1 -> addProductUI();
                    case 2 -> deleteProductUI();
                    case 3 -> productManager.showAll();
                    case 4 -> createOrderUI();
                    case 5 -> addProductToOrderUI();
                    case 6 -> showOrdersUI();
                    case 7 -> showOrderTotalUI();
                    case 8 -> demoMapTypes(); // để bạn thấy HashMap/LinkedHashMap/TreeMap
                    case 0 -> {
                        System.out.println("Thoát chương trình.");
                        return;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ!");
                }
            } finally {
                // finally: luôn chạy (dùng để minh họa)
                // Không nên in quá nhiều ở đây, nên để nhẹ.
            }
        }
    }

    // ===== MENU =====
    private static void printMenu() {
        System.out.println("\n========== QUẢN LÝ SẢN PHẨM & ĐƠN HÀNG ==========");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Xóa sản phẩm theo id");
        System.out.println("3. Hiển thị danh sách sản phẩm");
        System.out.println("4. Tạo đơn hàng (orderKey)");
        System.out.println("5. Thêm sản phẩm vào đơn hàng");
        System.out.println("6. Hiển thị danh sách đơn hàng");
        System.out.println("7. Tính tổng tiền 1 đơn hàng");
        System.out.println("8. Demo Map: HashMap vs LinkedHashMap vs TreeMap");
        System.out.println("0. Thoát");
        System.out.println("================================================");
    }

    // ===== UI FUNCTIONS =====

    private static void addProductUI() {
        System.out.println("\n--- THÊM SẢN PHẨM ---");
        int id = readInt("Nhập id: ");
        String name = readNonEmpty("Nhập tên: ");
        double price = readDouble("Nhập giá: ");

        try {
            productManager.add(new Product(id, name, price)); // có thể throw InvalidPriceException
            System.out.println("✅ Thêm sản phẩm thành công!");
        } catch (InvalidPriceException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteProductUI() {
        System.out.println("\n--- XÓA SẢN PHẨM ---");
        int id = readInt("Nhập id cần xóa: ");
        try {
            productManager.deleteById(id); // throws NotFoundException (Checked)
            System.out.println("✅ Xóa sản phẩm thành công!");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createOrderUI() {
        System.out.println("\n--- TẠO ĐƠN HÀNG ---");
        String orderKey = readNonEmpty("Nhập mã đơn (VD: DH001): ");
        int orderId = readInt("Nhập orderId (số): ");

        if (orders.containsKey(orderKey)) {
            System.out.println("Lỗi: Mã đơn đã tồn tại!");
            return;
        }

        orders.put(orderKey, new Order(orderId));
        System.out.println("✅ Tạo đơn hàng thành công!");
    }

    private static void addProductToOrderUI() {
        System.out.println("\n--- THÊM SẢN PHẨM VÀO ĐƠN ---");
        String orderKey = readNonEmpty("Nhập mã đơn: ");

        try {
            Order order = getOrderOrThrow(orderKey); // throws NotFoundException
            int productId = readInt("Nhập id sản phẩm: ");

            Product p = productManager.findById(productId); // throws NotFoundException
            order.addProduct(p);

            System.out.println("✅ Đã thêm vào đơn!");
            System.out.println("Đơn hiện tại: " + order);

        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showOrdersUI() {
        System.out.println("\n--- DANH SÁCH ĐƠN HÀNG ---");
        if (orders.isEmpty()) {
            System.out.println("(Chưa có đơn hàng)");
            return;
        }

        // Duyệt Map bằng entrySet
        for (Map.Entry<String, Order> entry : orders.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static void showOrderTotalUI() {
        System.out.println("\n--- TÍNH TỔNG TIỀN ĐƠN HÀNG ---");
        String orderKey = readNonEmpty("Nhập mã đơn: ");

        try {
            Order order = getOrderOrThrow(orderKey);
            System.out.println("Đơn: " + orderKey);
            System.out.println("Tổng tiền = " + String.format("%.2f", order.totalAmount()));
            System.out.println("Chi tiết sản phẩm:");
            order.getProducts().forEach(p -> System.out.println("- " + p));
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // ===== BUSINESS METHOD (throw/throws) =====
    private static Order getOrderOrThrow(String orderKey) throws NotFoundException {
        Order order = orders.get(orderKey);
        if (order == null) {
            throw new NotFoundException("Lỗi: Không tồn tại đơn hàng mã = " + orderKey);
        }
        return order;
    }

    // ===== DEMO MAP TYPES =====
    private static void demoMapTypes() {
        System.out.println("\n--- DEMO MAP TYPES ---");

        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new LinkedHashMap<>();
        Map<String, Integer> m3 = new TreeMap<>();

        String[] keys = {"DH003", "DH001", "DH010", "DH002"};

        for (int i = 0; i < keys.length; i++) {
            m1.put(keys[i], i);
            m2.put(keys[i], i);
            m3.put(keys[i], i);
        }

        System.out.println("HashMap (không đảm bảo thứ tự): " + m1);
        System.out.println("LinkedHashMap (giữ thứ tự thêm): " + m2);
        System.out.println("TreeMap (sắp xếp theo key): " + m3);
    }

    // ===== SAFE INPUT (Exception Handling) =====
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                return Double.parseDouble(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số hợp lệ (vd: 12000 hoặc 12000.5)!");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s == null || s.trim().isEmpty()) {
                System.out.println("Lỗi: Không được để trống!");
            } else return s.trim();
        }
    }

    // ===== DATA SAMPLE =====
    private static void seedData() {
        // try-catch để tránh dừng nếu lỡ seed sai
        try {
            productManager.add(new Product(1, "Bàn phím", 250000));
            productManager.add(new Product(2, "Chuột", 150000));
            productManager.add(new Product(3, "Tai nghe", 350000));
        } catch (InvalidPriceException e) {
            System.out.println("Seed lỗi: " + e.getMessage());
        }

        orders.put("DH001", new Order(101));
    }
}
