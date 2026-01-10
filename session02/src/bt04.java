import java.util.Scanner;

public class bt04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a;
        int b;
        int c;
        do {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            if ((a + b) < c || (c + a) < b || (b + c) < a) {
                System.out.println("Ba canh khong tao thanh tam giac");
            }
        } while ((a + b) < c || (c + a) < b || (b + c) < a);
        int a2 = a * a;
        int b2 = b * b;
        int c2 = c * c;
        if (a == b && b == c) {
            System.out.println("Day la tam giac deu");
        } else if (a == b || a == c || b == c) {
            System.out.println("Day la tam giac can");
        } else if (a2 + b2 == c2 || a2 + c2 == b2 || b2 + c2 == a2) {
            System.out.println("Day la tam giac vuong");
        } else {
            System.out.println("Day la tam giac thuong");
        }
    }
}
