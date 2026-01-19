import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class bt01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str =  sc.nextLine();
        str = str.trim();
        boolean isEmail = Pattern.matches("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\\\.[A-Za-z]{2,6}$", str);
        if (isEmail) {
            System.out.println("Email hợp lệ");
        } else {
            System.out.println("Email không hợp lệ");
        }
    }
}
