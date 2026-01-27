package bt05;

public class Dog extends Mammal {
    public Dog(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says: Woof Woof!\n", super.getName());
    }

    public void fetchBall() {
        System.out.println("Fetching Ball");
    }
}
