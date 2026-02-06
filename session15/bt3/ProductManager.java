package bt3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProductManager<T extends Product> {
    // Dùng List interface + LinkedList (để đúng yêu cầu ArrayList/LinkedList)
    private final List<T> products = new LinkedList<>();

    public List<T> getAll() {
        return products;
    }

    // Unchecked exception: giá <= 0
    public void add(T p) {
        if (p.getPrice() <= 0) {
            throw new InvalidPriceException("Lỗi: Giá sản phẩm phải > 0!");
        }
        products.add(p);
    }

    // Checked exception: xóa không tồn tại
    public void deleteById(int id) throws NotFoundException {
        Iterator<T> it = products.iterator();
        while (it.hasNext()) {
            T p = it.next();
            if (p.getId() == id) {
                it.remove();
                return;
            }
        }
        throw new NotFoundException("Lỗi: Không tìm thấy sản phẩm id = " + id);
    }

    public T findById(int id) throws NotFoundException {
        for (T p : products) {
            if (p.getId() == id) return p;
        }
        throw new NotFoundException("Lỗi: Không tìm thấy sản phẩm id = " + id);
    }

    // Duyệt collection nhiều cách
    public void showAll() {
        if (products.isEmpty()) {
            System.out.println("(Danh sách sản phẩm trống)");
            return;
        }

        System.out.println("\n--- Danh sách sản phẩm (for i) ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }

        System.out.println("\n--- Danh sách sản phẩm (enhanced for) ---");
        for (T p : products) {
            System.out.println("- " + p);
        }

        System.out.println("\n--- Danh sách sản phẩm (Iterator) ---");
        Iterator<T> it = products.iterator();
        while (it.hasNext()) {
            System.out.println("* " + it.next());
        }

        System.out.println("\n--- Danh sách sản phẩm (forEach + lambda) ---");
        products.forEach(p -> System.out.println("> " + p));
    }
}
