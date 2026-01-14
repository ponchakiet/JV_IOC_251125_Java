import java.util.Scanner;

public class bt02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int soHocVien = 0;
        double tongDiem = 0;
        double diemCaoNhat = -1;
        double diemThapNhat = 11;

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Nhập điểm học viên");
            System.out.println("2. Hiển thị thống kê");
            System.out.println("3. Thoát chương trình");
            System.out.print("Chọn chức năng: ");

            int chon = sc.nextInt();

            switch (chon) {
                case 1:
                    System.out.println("\n--- Nhập điểm học viên (-1 để kết thúc) ---");
                    while (true) {
                        System.out.print("Nhập điểm: ");
                        double diem = sc.nextDouble();

                        if (diem == -1) {
                            break;
                        }

                        if (diem < 0 || diem > 10) {
                            System.out.println("Điểm không hợp lệ! Vui lòng nhập từ 0 đến 10.");
                            continue;
                        }

                        System.out.print("Xếp loại: ");
                        if (diem < 5) {
                            System.out.println("Yếu");
                        } else if (diem < 7) {
                            System.out.println("Trung Bình");
                        } else if (diem < 8) {
                            System.out.println("Khá");
                        } else if (diem < 9) {
                            System.out.println("Giỏi");
                        } else {
                            System.out.println("Xuất sắc");
                        }

                        soHocVien++;
                        tongDiem += diem;

                        if (diem > diemCaoNhat) {
                            diemCaoNhat = diem;
                        }
                        if (diem < diemThapNhat) {
                            diemThapNhat = diem;
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Thống kê ---");
                    if (soHocVien == 0) {
                        System.out.println("Chưa có dữ liệu");
                    } else {
                        double diemTrungBinh = tongDiem / soHocVien;
                        System.out.println("Số học viên     : " + soHocVien);
                        System.out.printf("Điểm trung bình : %.2f%n", diemTrungBinh);
                        System.out.println("Điểm cao nhất   : " + diemCaoNhat);
                        System.out.println("Điểm thấp nhất  : " + diemThapNhat);
                    }
                    break;

                case 3:
                    System.out.println("Kết thúc chương trình!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠ Lựa chọn không hợp lệ!");
            }
        }
    }
}
