package bt1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Staff> staffList = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n===== MENU QUẢN LÝ NHÂN SỰ =====");
            System.out.println("1. Thêm mới nhân viên");
            System.out.println("2. Hiển thị danh sách");
            System.out.println("3. Cập nhật theo ID");
            System.out.println("4. Xóa theo ID");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng!");
                choice = 0;
            }

            switch (choice) {
                case 1: {
                    System.out.print("1. Giảng viên | 2. Nhân viên hành chính: ");
                    int type = Integer.parseInt(sc.nextLine());

                    System.out.print("Nhập ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    if (type == 1) {
                        Lecturer lec = new Lecturer(id);
                        lec.input(sc);
                        staffList.add(lec);
                    } else if (type == 2) {
                        AdminStaff ad = new AdminStaff(id);
                        ad.input(sc);
                        staffList.add(ad);
                    } else {
                        System.out.println("Loại không hợp lệ!");
                    }
                    break;
                }

                case 2: {
                    if (staffList.isEmpty()) {
                        System.out.println("Danh sách rỗng!");
                    } else {
                        for (Staff s : staffList) {
                            s.display(); // đa hình runtime
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.print("Nhập ID cần sửa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean found = false;

                    for (Staff s : staffList) {
                        if (s.getId() == id) {
                            if (s instanceof Lecturer lec) {
                                lec.input(sc);
                            } else if (s instanceof AdminStaff ad) {
                                ad.input(sc);
                            }
                            System.out.println("Cập nhật thành công!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Không tìm thấy ID!");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Nhập ID cần xóa: ");
                    int id = Integer.parseInt(sc.nextLine());

                    boolean removed = staffList.removeIf(s -> s.getId() == id);

                    if (removed) {
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Không tìm thấy ID!");
                    }
                    break;
                }

                case 5:
                    System.out.println("Thoát chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 5);
    }
}
