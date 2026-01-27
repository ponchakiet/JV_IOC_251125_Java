package bt05;

public class Cat extends Mammal {
    public Cat(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says: Meow Meow!\n", super.getName());
    }

    public void climbTree() {
        System.out.println("Climbing Tree");
    }
}
