import java.util.Scanner;

public class bt01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tên khách hàng: ");
        String tenKhachHang = sc.nextLine();

        System.out.print("Nhập tên sản phẩm: ");
        String tenSanPham = sc.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double gia = sc.nextDouble();

        System.out.print("Nhập số lượng mua: ");
        int soLuong = sc.nextInt();

        System.out.print("Khách có thẻ thành viên không (true/false): ");
        boolean laThanhVien = sc.nextBoolean();

        double thanhTien = gia * soLuong;
        double giamGia = laThanhVien ? thanhTien * 0.10 : 0;
        double vat = (thanhTien - giamGia) * 0.08;
        double tongThanhToan = thanhTien - giamGia + vat;
        System.out.println("\n===== HÓA ĐƠN =====");
        System.out.println("Khách hàng : " + tenKhachHang);
        System.out.println("Sản phẩm   : " + tenSanPham);
        System.out.println("Số lượng   : " + soLuong);
        System.out.printf("Đơn giá    : %.2f%n", gia);
        System.out.printf("Thành tiền : %.2f%n", thanhTien);
        System.out.printf("Giảm giá   : %.2f%n", giamGia);
        System.out.printf("VAT (8%%)   : %.2f%n", vat);
        System.out.printf("Tổng tiền  : %.2f%n", tongThanhToan);
    }
}
