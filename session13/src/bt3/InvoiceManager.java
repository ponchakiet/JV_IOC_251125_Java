package bt3;

import java.util.ArrayList;

public class InvoiceManager implements Manage<Invoice> {
    private ArrayList<Invoice> invoices = new ArrayList<>();

    @Override
    public void add(Invoice item) {
        invoices.add(item);
        System.out.println("✔ Đã thêm hóa đơn");
    }

    @Override
    public void update(int index, Invoice item) {
        if (index >= 0 && index < invoices.size()) {
            invoices.set(index, item);
            System.out.println("✔ Đã cập nhật hóa đơn");
        } else {
            System.out.println("✖ Vị trí không hợp lệ");
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < invoices.size()) {
            invoices.remove(index);
            System.out.println("✔ Đã xóa hóa đơn");
        } else {
            System.out.println("✖ Vị trí không hợp lệ");
        }
    }

    @Override
    public void display() {
        if (invoices.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống");
            return;
        }

        System.out.println("\n--- DANH SÁCH HÓA ĐƠN ---");
        for (int i = 0; i < invoices.size(); i++) {
            System.out.println(i + ". " + invoices.get(i));
        }
    }
}
