package bt05;

public class Elephant extends Mammal{
    public Elephant(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says: Pawooooo!\n", super.getName());
    }

    public void sprayWater() {
        System.out.println("Spray Water");
    }
}
