package bt04;

public class Main {
    public static void main(String[] args) {
        Car c =  new Car();
        c.accelerate();
        c.printStatus();
        c.accelerate(20);
        c.printStatus();
        c.accelerate(20,1);
        c.printStatus();
    }
}
