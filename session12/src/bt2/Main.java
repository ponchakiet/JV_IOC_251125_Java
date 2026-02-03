package bt2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void showValue(Asset a) {
        System.out.println("Giá trị hiện tại: " + a.getMarketValue());
    }

    // overloading
    public static void search(ArrayList<Asset> list, String code) {
        for (Asset a : list) {
            if (a.getAssetCode().equalsIgnoreCase(code)) {
                a.display();
                return;
            }
        }
        System.out.println("Không tìm thấy tài sản!");
    }

    public static void search(ArrayList<Asset> list, double price) {
        boolean found = false;
        for (Asset a : list) {
            if (a.getPurchasePrice() > price) {
                a.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có tài sản nào thỏa điều kiện!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Asset> list = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== MENU QUẢN LÝ TÀI SẢN =====");
            System.out.println("1. Nhập tài sản");
            System.out.println("2. Xuất báo cáo");
            System.out.println("3. Tìm kiếm");
            System.out.println("4. Sửa giá mua");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: {
                    System.out.print("1. Máy tính | 2. Thiết bị mạng: ");
                    int type = Integer.parseInt(sc.nextLine());

                    System.out.print("Mã tài sản: ");
                    String code = sc.nextLine();
                    System.out.print("Tên: ");
                    String name = sc.nextLine();
                    System.out.print("Giá mua: ");
                    double price = Double.parseDouble(sc.nextLine());

                    if (type == 1) {
                        System.out.print("RAM (GB): ");
                        int ram = Integer.parseInt(sc.nextLine());
                        System.out.print("CPU: ");
                        String cpu = sc.nextLine();
                        list.add(new Computer(code, name, price, ram, cpu));
                    } else if (type == 2) {
                        System.out.print("Số cổng: ");
                        int ports = Integer.parseInt(sc.nextLine());
                        list.add(new NetworkDevice(code, name, price, ports));
                    }
                    break;
                }

                case 2: {
                    for (Asset a : list) {
                        a.display(); // đa hình
                    }
                    break;
                }

                case 3: {
                    System.out.print("1. Tìm theo mã | 2. Tìm theo giá > ");
                    int t = Integer.parseInt(sc.nextLine());

                    if (t == 1) {
                        System.out.print("Nhập mã: ");
                        search(list, sc.nextLine());
                    } else {
                        System.out.print("Nhập giá: ");
                        search(list, Double.parseDouble(sc.nextLine()));
                    }
                    break;
                }

                case 4: {
                    System.out.print("Nhập mã cần sửa: ");
                    String code = sc.nextLine();
                    for (Asset a : list) {
                        if (a.getAssetCode().equalsIgnoreCase(code)) {
                            System.out.print("Giá mới: ");
                            a.setPurchasePrice(Double.parseDouble(sc.nextLine()));
                            System.out.println("Cập nhật thành công!");
                            break;
                        }
                    }
                    break;
                }

                case 5:
                    System.out.println("Thoát!");
                    break;
            }
        } while (choice != 5);
    }
}
