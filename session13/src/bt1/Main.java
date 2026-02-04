package bt1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersonManagement p = new PersonManagement();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Thêm người dùng");
            System.out.println("2. Xóa người dùng theo email");
            System.out.println("3. Hiển thị danh sách");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    p.addPerson(sc);
                    break;
                case 2:
                    p.removePerson(sc);
                    break;
                case 3:
                    p.showAllPersons();
                    break;
                case 4:
                    System.out.println("Thoát chương trình");
                    System.exit(0);
                default:
                    System.out.println("Chọn sai!");
                    break;
            }
        } while (choice != 4);
    }
}
