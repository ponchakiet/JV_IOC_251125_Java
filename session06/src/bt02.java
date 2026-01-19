import java.util.Scanner;
import java.util.regex.Pattern;

public class bt02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String hoTen = "";
        String email = "";
        String dienThoai = "";
        String matKhau = "";
        int choice;
        do {
            System.out.println("\n*************** QUẢN LÝ NGƯỜI DÙNG ***************");
            System.out.println("1. Nhập thông tin người dùng");
            System.out.println("2. Chuẩn hóa họ tên");
            System.out.println("3. Kiểm tra email hợp lệ");
            System.out.println("4. Kiểm tra số điện thoại hợp lệ");
            System.out.println("5. Kiểm tra mật khẩu hợp lệ");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập họ tên: ");
                    hoTen = sc.nextLine();
                    System.out.print("Nhập email: ");
                    email = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    dienThoai = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    matKhau = sc.nextLine();
                    break;
                case 2:
                    if (hoTen.isEmpty()) {
                        System.out.println("Chưa nhập họ tên!");
                    } else {
                        hoTen = chuanHoaHoTen(hoTen);
                        System.out.println("Họ tên sau chuẩn hóa: " + hoTen);
                    }
                    break;
                case 3:
                    if (email.isEmpty()) {
                        System.out.println("Chưa nhập email!");
                    } else if (kiemTraEmail(email)) {
                        System.out.println("Email hợp lệ");
                    } else {
                        System.out.println("Email không hợp lệ");
                    }
                    break;
                case 4:
                    if (dienThoai.isEmpty()) {
                        System.out.println("Chưa nhập số điện thoại!");
                    } else if (kiemTraDienThoai(dienThoai)) {
                        System.out.println("Số điện thoại hợp lệ");
                    } else {
                        System.out.println("Số điện thoại không hợp lệ");
                    }
                    break;
                case 5:
                    if (matKhau.isEmpty()) {
                        System.out.println("Chưa nhập mật khẩu!");
                    } else if (kiemTraMatKhau(matKhau)) {
                        System.out.println("Mật khẩu hợp lệ");
                    } else {
                        System.out.println("Mật khẩu không hợp lệ");
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
    public static String chuanHoaHoTen(String s) {
        s = s.trim().replaceAll("\\s+", " ");
        String[] arr = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : arr) {
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return result.toString().trim();
    }
    public static boolean kiemTraEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(regex, email);
    }

    public static boolean kiemTraDienThoai(String phone) {
        String regex = "^(03|05|07|08|09)[0-9]{8}$";
        return Pattern.matches(regex, phone);
    }

    public static boolean kiemTraMatKhau(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$!%]).{8,}$";
        return Pattern.matches(regex, password);
    }
}
