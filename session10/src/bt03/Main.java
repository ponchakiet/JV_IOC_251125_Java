package bt03;

public class Main {
    public static void main(String[] args) {
        Computer c = new Computer(1, 1000, 0.1, 50);
        System.out.println(c.calculatePrice(1000));
        System.out.println(c.calculatePrice(1000, 0.1));
        System.out.println(c.calculatePrice(1000, 0.1, 50));
    }
}
