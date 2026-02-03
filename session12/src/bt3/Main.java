package bt3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Drink> menu = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== MENU QUÁN CÀ PHÊ =====");
            System.out.println("1. Thêm món");
            System.out.println("2. Hiển thị menu");
            System.out.println("3. Áp dụng giảm giá");
            System.out.println("4. Xóa món");
            System.out.println("5. Thống kê giá trung bình");
            System.out.println("6. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: {
                    System.out.print("1. Cà phê | 2. Trà trái cây: ");
                    int type = Integer.parseInt(sc.nextLine());

                    System.out.print("ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Tên món: ");
                    String name = sc.nextLine();
                    System.out.print("Giá: ");
                    double price = Double.parseDouble(sc.nextLine());

                    if (type == 1) {
                        menu.add(new Coffee(id, name, price));
                    } else if (type == 2) {
                        menu.add(new FruitTea(id, name, price));
                    }
                    break;
                }

                case 2: {
                    if (menu.isEmpty()) {
                        System.out.println("Menu trống!");
                    } else {
                        for (Drink d : menu) {
                            d.display(); // đa hình
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.print("Nhập % giảm giá: ");
                    double percent = Double.parseDouble(sc.nextLine());

                    for (Drink d : menu) {
                        d.applyDiscount(percent); // interface
                    }
                    System.out.println("Đã áp dụng giảm giá!");
                    break;
                }

                case 4: {
                    System.out.print("Nhập ID cần xóa: ");
                    int id = Integer.parseInt(sc.nextLine());

                    boolean removed = menu.removeIf(d -> d.getId() == id);

                    if (removed) {
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Không tìm thấy món!");
                    }
                    break;
                }

                case 5: {
                    if (menu.isEmpty()) {
                        System.out.println("Không có món nào!");
                    } else {
                        double sum = 0;
                        for (Drink d : menu) {
                            sum += d.getPrice();
                        }
                        System.out.println("Giá trung bình: " + (sum / menu.size()));
                    }
                    break;
                }

                case 6:
                    System.out.println("Thoát chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 6);
    }
}
