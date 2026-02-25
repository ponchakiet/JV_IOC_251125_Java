import java.util.InputMismatchException;
import java.util.Scanner;

public class bt3 {
    public static void main(String[] args) {
        final long SO_DU_TOI_THIEU = 50_000;
        long soDu = 1_000_000;

        Scanner sc = new Scanner(System.in);

        System.out.println("===== MÔ PHỎNG RÚT TIỀN =====");
        System.out.println("Số dư hiện tại: " + soDu + " đồng");
        System.out.println("Số dư tối thiểu phải duy trì: " + SO_DU_TOI_THIEU + " đồng");

        while (true) {
            try {
                System.out.print("\nNhập số tiền muốn rút: ");
                long soTienRut = sc.nextLong(); // nhập sai kiểu -> InputMismatchException

                // (Tùy chọn) kiểm tra số tiền rút phải > 0 cho hợp lý
                if (soTienRut <= 0) {
                    System.out.println("Lỗi: Số tiền rút phải lớn hơn 0!");
                    continue;
                }

                // 1) Rút vượt quá số dư
                if (soTienRut > soDu) {
                    System.out.println("Lỗi: Số tiền rút vượt quá số dư!");
                    continue;
                }

                // 2) Rút xong mà số dư < 50.000
                long soDuConLai = soDu - soTienRut;
                if (soDuConLai < SO_DU_TOI_THIEU) {
                    System.out.println("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
                    continue;
                }

                // 3) Thành công
                soDu = soDuConLai;
                System.out.println("Rút tiền thành công!");
                System.out.println("Số tiền đã rút: " + soTienRut + " đồng");
                System.out.println("Số dư còn lại: " + soDu + " đồng");
                break; // kết thúc chương trình sau giao dịch thành công

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
                sc.nextLine(); // xóa dữ liệu sai còn kẹt trong bộ đệm để tránh lặp lỗi
            }
        }

        sc.close();
    }
}
