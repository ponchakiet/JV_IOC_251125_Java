import java.util.Scanner;

public class bt02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số thứ nhất:");
        int firstNumber = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập số thứ hai:");
        int secondNumber = Integer.parseInt(sc.nextLine());
        System.out.println("--- Kết quả ---");
        System.out.println("firstNumber: " + firstNumber);
        System.out.println("secondNumber: " + secondNumber);
        System.out.println("Tổng = " + (firstNumber + secondNumber));
        System.out.println("Hiệu = "+ (firstNumber - secondNumber));
        System.out.println("Tích = "+ (firstNumber * secondNumber));
        System.out.println("Thương = "+ (firstNumber / secondNumber));
        System.out.println("Phần dư = "+ (firstNumber % secondNumber));
    }
}
