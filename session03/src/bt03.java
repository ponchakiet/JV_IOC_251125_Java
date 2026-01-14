import java.util.Scanner;

public class bt03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tongNhanVien = 0;
        double tongLuong = 0;
        double luongCaoNhat = -1;
        double luongThapNhat = Double.MAX_VALUE;
        double tongThuong = 0;

        while (true) {
            // Menu
            System.out.println("\n===== MENU QUẢN LÝ LƯƠNG =====");
            System.out.println("1. Nhập lương nhân viên");
            System.out.println("2. Hiển thị thống kê");
            System.out.println("3. Tính tổng tiền thưởng");
            System.out.println("4. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            int chon = sc.nextInt();

            switch (chon) {
                case 1:
                    System.out.println("\n--- Nhập lương nhân viên (-1 để kết thúc) ---");
                    while (true) {
                        System.out.print("Nhập lương: ");
                        double luong = sc.nextDouble();

                        if (luong == -1) {
                            break;
                        }

                        if (luong < 0 || luong > 500_000_000) {
                            System.out.println("⚠ Lương không hợp lệ (0 - 500 triệu)");
                            continue;
                        }

                        System.out.print("Phân loại: ");
                        if (luong < 5_000_000) {
                            System.out.println("Thu nhập thấp");
                        } else if (luong < 15_000_000) {
                            System.out.println("Thu nhập trung bình");
                        } else if (luong < 50_000_000) {
                            System.out.println("Thu nhập khá");
                        } else {
                            System.out.println("Thu nhập cao");
                        }

                        double thuong;
                        if (luong < 5_000_000) {
                            thuong = luong * 0.05;
                        } else if (luong < 15_000_000) {
                            thuong = luong * 0.10;
                        } else if (luong < 50_000_000) {
                            thuong = luong * 0.15;
                        } else if (luong <= 100_000_000) {
                            thuong = luong * 0.20;
                        } else {
                            thuong = luong * 0.25;
                        }

                        tongThuong += thuong;

                        tongNhanVien++;
                        tongLuong += luong;

                        if (luong > luongCaoNhat) {
                            luongCaoNhat = luong;
                        }
                        if (luong < luongThapNhat) {
                            luongThapNhat = luong;
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Thống kê lương ---");
                    if (tongNhanVien == 0) {
                        System.out.println("Chưa có dữ liệu");
                    } else {
                        System.out.println("Số nhân viên   : " + tongNhanVien);
                        System.out.printf("Lương TB       : %.2f%n", tongLuong / tongNhanVien);
                        System.out.println("Lương cao nhất : " + luongCaoNhat);
                        System.out.println("Lương thấp nhất: " + luongThapNhat);
                        System.out.println("Tổng tiền lương: " + tongLuong);
                    }
                    break;

                case 3:
                    System.out.println("\n--- Tổng tiền thưởng ---");
                    if (tongNhanVien == 0) {
                        System.out.println("Chưa có dữ liệu");
                    } else {
                        System.out.printf("Tổng tiền thưởng cho nhân viên: %.2f%n", tongThuong);
                    }
                    break;

                case 4:
                    System.out.println("Kết thúc chương trình!");
                    System.exit(0);

                default:
                    System.out.println("⚠ Lựa chọn không hợp lệ!");
            }
        }
    }
}
