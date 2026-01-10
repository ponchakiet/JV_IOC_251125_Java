import java.util.Scanner;

public class bt01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            n = Integer.parseInt(sc.nextLine());
            if (n < 0) {
                System.out.println("So nhap vao khong hop le");
            }
        } while (n < 0);
        int tong = 0;
        for (int i = 1; i <= n; i++) {
            tong += i;
        }
        System.out.printf("Tong cac so tu 1 toi %d: %d", n, tong);
    }
}
