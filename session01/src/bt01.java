import java.util.Scanner;

public class bt01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bán kính:");
        int r = Integer.parseInt(sc.nextLine());
        double A = Math.PI * r * r;
        System.out.printf("Diện tích : %.2f", A);
    }
}
