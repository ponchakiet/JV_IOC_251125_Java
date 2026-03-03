import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final ProductDAO dao = new ProductDAO();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Chọn: ");

            try {
                switch (choice) {
                    case 1 -> listProducts();
                    case 2 -> addProduct();
                    case 3 -> updateProduct();
                    case 4 -> deleteProduct();
                    case 5 -> searchProduct();
                    case 6 -> sortByPrice();
                    case 7 -> statByCatalog();
                    case 8 -> {
                        System.out.println("Thoát!");
                        return;
                    }
                    default -> System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Validate lỗi: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }

            System.out.println();
        }
    }

    static void printMenu() {
        System.out.println("********************PRODUCT MANAGEMENT****************");
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Thêm mới sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm theo tên sản phẩm");
        System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
        System.out.println("7. Thống kê số lượng sản phẩm theo danh mục");
        System.out.println("8. Thoát");
    }

    static void listProducts() throws Exception {
        List<Product> products = dao.getAll();
        System.out.println("=== DANH SÁCH SẢN PHẨM ===");
        if (products.isEmpty()) System.out.println("(Trống)");
        else products.forEach(System.out::println);
    }

    static void addProduct() throws Exception {
        String name = readLine("Tên sản phẩm: ");
        double price = readDouble("Giá: ");
        String title = readLine("Tiêu đề: ");
        LocalDate created = readDate("Ngày tạo (yyyy-MM-dd): ");
        String catalog = readLine("Danh mục: ");
        boolean status = readBoolean("Trạng thái (1=on, 0=off): ");

        dao.add(name, price, title, created, catalog, status);
        System.out.println("Thêm thành công!");
    }

    static void updateProduct() throws Exception {
        int id = readInt("Nhập Product_Id cần cập nhật: ");
        Product old = dao.getById(id);
        if (old == null) {
            System.out.println("Không tìm thấy sản phẩm.");
            return;
        }
        System.out.println("Hiện tại: " + old);

        String name = readLine("Tên mới: ");
        double price = readDouble("Giá mới: ");
        String title = readLine("Tiêu đề mới: ");
        LocalDate created = readDate("Ngày tạo mới (yyyy-MM-dd): ");
        String catalog = readLine("Danh mục mới: ");
        boolean status = readBoolean("Trạng thái (1=on, 0=off): ");

        dao.update(id, name, price, title, created, catalog, status);
        System.out.println("Cập nhật thành công!");
    }

    static void deleteProduct() throws Exception {
        int id = readInt("Nhập Product_Id cần xóa: ");
        dao.delete(id);
        System.out.println("Xóa thành công!");
    }

    static void searchProduct() throws Exception {
        String kw = readLine("Nhập từ khóa tên: ");
        List<Product> list = dao.searchByName(kw);
        if (list.isEmpty()) System.out.println("Không có kết quả.");
        else list.forEach(System.out::println);
    }

    static void sortByPrice() throws Exception {
        List<Product> list = dao.sortByPriceAsc();
        if (list.isEmpty()) System.out.println("(Trống)");
        else list.forEach(System.out::println);
    }

    static void statByCatalog() throws Exception {
        Map<String, Long> m = dao.countByCatalog();
        if (m.isEmpty()) {
            System.out.println("(Trống)");
            return;
        }
        System.out.println("=== THỐNG KÊ THEO DANH MỤC ===");
        m.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    // ===== Helpers =====
    static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("Nhập số nguyên hợp lệ."); }
        }
    }

    static double readDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("Nhập số hợp lệ."); }
        }
    }

    static boolean readBoolean(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (s.equals("1")) return true;
            if (s.equals("0")) return false;
            System.out.println("Chỉ nhập 1 hoặc 0.");
        }
    }

    static LocalDate readDate(String msg) {
        while (true) {
            System.out.print(msg);
            try { return LocalDate.parse(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("Sai định dạng. Ví dụ: 2026-03-03"); }
        }
    }

    static String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}