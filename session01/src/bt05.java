import java.util.Scanner;

public class bt05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập cân nặng");
        int kg = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập chiều cao");
        double m = Double.parseDouble(sc.nextLine());
        double BMI = kg / Math.pow(m, 2);
        System.out.printf("Chỉ số BMI: %.2f", BMI);
    }
}
