import java.util.Scanner;
import java.util.Arrays;

public class bt03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] bienSo = new String[100];
        int n = 0;
        int choice;
        do {
            System.out.println("\n*************** QUẢN LÝ BIỂN SỐ XE ***************");
            System.out.println("1. Thêm các biển số xe");
            System.out.println("2. Hiển thị danh sách biển số xe");
            System.out.println("3. Tìm kiếm biển số xe");
            System.out.println("4. Tìm biển số xe theo mã tỉnh");
            System.out.println("5. Sắp xếp biển số xe tăng dần");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng biển số xe: ");
                    int k = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < k; i++) {
                        System.out.print("Nhập biển số xe " + (n + 1) + ": ");
                        bienSo[n++] = sc.nextLine();
                    }
                    break;
                case 2:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                    } else {
                        System.out.println("Danh sách biển số xe:");
                        for (int i = 0; i < n; i++) {
                            System.out.println((i + 1) + ". " + bienSo[i]);
                        }
                    }
                    break;
                case 3:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                    } else {
                        System.out.print("Nhập biển số xe cần tìm: ");
                        String tim = sc.nextLine();
                        boolean found = false;
                        for (int i = 0; i < n; i++) {
                            if (bienSo[i].equalsIgnoreCase(tim)) {
                                System.out.println("Tìm thấy: " + bienSo[i]);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Không tìm thấy biển số xe!");
                        }
                    }
                    break;
                case 4:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                    } else {
                        System.out.print("Nhập mã tỉnh (VD: 29, 30, 51...): ");
                        String maTinh = sc.nextLine();
                        boolean found = false;
                        System.out.println("Các biển số xe thuộc tỉnh " + maTinh + ":");
                        for (int i = 0; i < n; i++) {
                            if (bienSo[i].startsWith(maTinh)) {
                                System.out.println(bienSo[i]);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Không có biển số xe nào thuộc tỉnh này!");
                        }
                    }
                    break;
                case 5:
                    if (n == 0) {
                        System.out.println("Chưa có dữ liệu!");
                    } else {
                        Arrays.sort(bienSo, 0, n);
                        System.out.println("Đã sắp xếp biển số xe tăng dần!");
                    }
                    break;
                case 6:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 6);
    }
}
