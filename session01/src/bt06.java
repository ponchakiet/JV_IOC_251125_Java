import java.util.Scanner;

public class bt06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap van toc (km/h): ");
        float vanToc = sc.nextFloat();
        System.out.print("Nhap thoi gian di (gio): ");
        float thoiGian = sc.nextFloat();
        if (vanToc < 0 || thoiGian < 0) {
            System.out.println("Van toc va thoi gian khong duoc nho hon 0!");
        } else {
            float quangDuong = vanToc * thoiGian;
            System.out.println("Quang duong di duoc la: " + quangDuong + " km");
        }
    }
}
