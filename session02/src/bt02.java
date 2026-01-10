import java.util.Scanner;

public class bt02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switch (n) {
            case 1, 3, 5, 7, 8, 10, 12:
                System.out.printf("Thang %d co 31 ngay", n);
                break;
            case 4, 6, 9, 11:
                System.out.printf("Thang %d co 30 ngay", n);
                break;
            case 2:
                System.out.printf("Thang %d co 28 hoac 29 ngay", n);
                break;
            default:
                System.out.println("Thang khong hop le");
        }
    }
}
