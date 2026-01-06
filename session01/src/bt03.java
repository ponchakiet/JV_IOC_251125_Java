import java.util.Scanner;

public class bt03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu so phan so thu nhat: ");
        int a = sc.nextInt();
        System.out.print("Nhap mau so phan so thu nhat: ");
        int b = sc.nextInt();
        System.out.print("Nhap tu so phan so thu hai: ");
        int c = sc.nextInt();
        System.out.print("Nhap mau so phan so thu hai: ");
        int d = sc.nextInt();
        int tuSoTong = a * d + c * b;
        int mauSoTong = b * d;
        System.out.println("Tong hai phan so la: " + tuSoTong + "/" + mauSoTong);
    }
}
