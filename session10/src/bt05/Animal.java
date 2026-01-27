package bt05;

public class Animal {
    private String name;
    private int age;

    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void showInfo() {
        System.out.printf("Name: %s, Age: %d\n", name, age);
    }

    public void makeSound() {
        System.out.println("Making Sound\n");
    }
    public void eat() {
        System.out.printf("%s is eating.\n", name);
    }

    public void eat(String food) {
        System.out.printf("%s is eating %s.\n", name, food);
    }
}
