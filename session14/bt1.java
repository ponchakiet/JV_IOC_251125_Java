import java.util.InputMismatchException;
import java.util.Scanner;

public class bt1 {
    // Hàm kiểm tra số nguyên tố
    static boolean isPrime(int n) {
        if (n < 2) return false;          // 0, 1 không phải nguyên tố
        if (n == 2) return true;          // 2 là nguyên tố
        if (n % 2 == 0) return false;     // số chẵn > 2 không phải nguyên tố

        // Chỉ kiểm tra ước lẻ tới sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhập một số nguyên dương (> 0): ");
            int n = sc.nextInt(); // nếu nhập chữ/ký tự/số thực -> lỗi

            // Kiểm tra hợp lệ
            if (n <= 0) {
                System.out.println("Lỗi: Số nhập vào không hợp lệ (phải > 0) để kiểm tra số nguyên tố.");
            } else {
                // Kiểm tra số nguyên tố
                if (isPrime(n)) {
                    System.out.println(n + " là số nguyên tố.");
                } else {
                    System.out.println(n + " không phải là số nguyên tố.");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Lỗi: Bạn phải nhập một SỐ NGUYÊN (không phải chữ, ký tự đặc biệt hoặc số thực).");
        } finally {
            sc.close();
        }
    }
}
