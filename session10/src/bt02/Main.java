package bt02;

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Rectangle(2, 3);
        Shape s2 = new Circle(1);
        System.out.println(s1.area());
        System.out.println(s2.area());
    }
}
