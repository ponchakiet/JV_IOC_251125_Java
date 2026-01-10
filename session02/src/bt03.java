import java.util.Scanner;

public class bt03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int tong = 0;
        while (n != 0) {
            if (n < 0) {
                n = n * -1;
            }
            int du = n % 10;
            tong += du;
            n /= 10;
        }
        System.out.printf("Tong cac chu so la: %d", tong);
    }
}
