package bt2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskManagement manager = new TaskManagement();

        while (true) {
            System.out.println("\n===== TODO MANAGEMENT =====");
            System.out.println("1. Thêm công việc");
            System.out.println("2. Liệt kê");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thống kê");
            System.out.println("0. Thoát");

            System.out.print("Chọn: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {

                    case "1":
                        System.out.print("Tên công việc: ");
                        String name = scanner.nextLine();

                        if (name.isEmpty())
                            throw new IllegalArgumentException("Không được để trống!");

                        System.out.print("Trạng thái (chưa hoàn thành/đã hoàn thành): ");
                        String status = scanner.nextLine();

                        manager.addTask(name, status);
                        break;

                    case "2":
                        manager.listTasks();
                        break;

                    case "3":
                        System.out.print("ID: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Trạng thái mới: ");
                        String newStatus = scanner.nextLine();

                        manager.updateTaskStatus(id, newStatus);
                        break;

                    case "4":
                        System.out.print("ID: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        manager.deleteTask(deleteId);
                        break;

                    case "5":
                        System.out.print("Tên cần tìm: ");
                        String search = scanner.nextLine();
                        manager.searchTaskByName(search);
                        break;

                    case "6":
                        manager.taskStatistics();
                        break;

                    case "0":
                        System.exit(0);

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {
                System.out.println("ID phải là số!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}