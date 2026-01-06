import java.util.Scanner;

public class bt04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float width, height;
        float area, perimeter;
        System.out.print("Nhap chieu rong: ");
        width = sc.nextFloat();
        System.out.print("Nhap chieu cao: ");
        height = sc.nextFloat();
        area = width * height;
        perimeter = 2 * (width + height);
        System.out.println("Dien tich hinh chu nhat: " + area);
        System.out.println("Chu vi hinh chu nhat: " + perimeter);
    }
}
