import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bt2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> inputs = new ArrayList<>();
        List<Integer> validNumbers = new ArrayList<>();
        int invalidCount = 0;

        System.out.println("Nhập các chuỗi (gõ 'exit' để kết thúc):");

        // 1) Nhập danh sách chuỗi
        while (true) {
            System.out.print("> ");
            String s = sc.nextLine();

            if (s.equalsIgnoreCase("exit")) {
                break;
            }

            inputs.add(s);
        }

        // 2) Chuyển đổi và phân loại
        for (String s : inputs) {
            String trimmed = s.trim(); // bỏ khoảng trắng đầu/cuối

            try {
                int n = Integer.parseInt(trimmed); // có thể ném NumberFormatException
                validNumbers.add(n);
            } catch (NumberFormatException e) {
                invalidCount++;
            }
        }

        // 3) Thống kê và hiển thị
        int validCount = validNumbers.size();

        System.out.println("\n===== KẾT QUẢ =====");
        System.out.println("Số lượng chuỗi hợp lệ: " + validCount);
        System.out.println("Số lượng chuỗi không hợp lệ: " + invalidCount);
        System.out.println("Danh sách số nguyên hợp lệ: " + validNumbers);

        sc.close();
    }
}
