import java.util.Random;
import java.util.Scanner;

public class bt04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Nhập độ dài chuỗi n: ");
        int n = sc.nextInt();
        if (n < 1 || n > 1000) {
            System.out.println("Giá trị n không hợp lệ");
            return;
        }
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz"
                + "0123456789";
        String result = null;
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(characters.length());
            result = result + characters.charAt(index);
        }
        System.out.println("Chuỗi ngẫu nhiên:");
        System.out.println(result);
    }
}
