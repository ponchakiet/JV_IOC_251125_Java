import java.util.Scanner;

public class bt06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        do {
            n = sc.nextInt();
            if (n < 0) {
                System.out.println("So nhap vao khong hop le");
            }
        } while (n < 0);
        for (int i = 0; i <= n; i++) {
            int tongchuso = 0;
            int tong = 0;
            int temp = i;
            while (temp > 0) {
                tongchuso++;
                temp = temp / 10;
            }
            temp = i;
            while (temp >  0) {
                int k = temp % 10;
                tong += Math.pow(k, tongchuso);
                temp = temp / 10;
            }
            if (i == tong) {
                System.out.printf("%d ", i);
            }
        }
    }
}
