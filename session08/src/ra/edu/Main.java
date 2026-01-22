package ra.edu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        while (true){
            System.out.println("----- Quản Lý Sinh Viên -----");
            System.out.println("""
                    1. Hiển thị danh sách sinh viên
                    2. Thêm mới 1 sinh viên
                    3. Chỉnh sửa thông tin sinh viên
                    4. Xóa sinh viên
                    5. Thoát
                    """);
            System.out.println("Chọn chức năng: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice){
                case   1:
                    System.out.println("Hiển thị danh sách sinh viên");
                    manager.showListStudent();
                    break;
                case   2:
                    System.out.println("Thêm mới 1 sinh viên");
                    manager.addStudent();
                    break;
                case   3:
                    System.out.println("Chỉnh sửa thông tin sinh viên");
                    System.out.println("Nhập id thông tin sinh viên cần chỉnh sửa: ");
                    int n = Integer.parseInt(sc.nextLine());
                    manager.updateStudent(n);
                    break;
                case   4:
                    System.out.println("Xóa sinh viên");
                    System.out.println("Nhập id thông tin sinh viên cần chỉnh sửa: ");
                    int s = Integer.parseInt(sc.nextLine());
                    manager.deleteStudent(s);
                    break;
                case   5:
                    System.out.println("Thoát chương trình");
                    sc.close();
                    return;
                default:
                    System.out.println("Chức năng không hợp lệ, vui lòng chọn lại!");
            }
        }
    }
}