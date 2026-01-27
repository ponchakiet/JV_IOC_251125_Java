package bt05;

import java.util.Scanner;

public class ZooApp {
    public static void main(String[] args) {
        int choose;
        Scanner sc = new Scanner(System.in);

        Animal[] list;
        int maxAnimal;
        do {
            System.out.println("Nhập số lượng thú tối đa cần quản lý: ");
            maxAnimal = sc.nextInt();
            if (maxAnimal < 1) {
                System.out.println("Số thú cần quản lý phải >0");
            }
        } while (maxAnimal < 1);

        list = new Animal[maxAnimal];

        int totalAnimal = 0;
        while (true) {
            System.out.println("================= ZOO MANAGEMENT MENU ==============");
            System.out.println("1. Tạo đối tượng và hiển thị thông tin (Kế thừa + super)");
            System.out.println("2. Kiểm tra Overriding: gọi makeSound() của từng con vật");
            System.out.println("3. Kiểm tra Overloading: gọi eat() và eat(String)");
            System.out.println("4. Kiểm tra đa hình runtime (Animal array)");
            System.out.println("5. Gọi phương thức đặc trưng của từng loài");
            System.out.println("6. Thoát");
            System.out.println("=======================================================");
            System.out.println("Chọn chức năng: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("--- THÔNG TIN CÁC ĐỘNG VẬT ---");
                    list[totalAnimal++] = new Dog("Buddy", 3, true);
                    list[totalAnimal++] = new Cat("Mimi", 2, true);
                    list[totalAnimal++] = new Elephant("Dumbo", 10, false);
                    for (int i = 0; i < totalAnimal; i++) {
                        Animal a = list[i];
                        if (a instanceof Dog) {
                            Dog d = (Dog) a;
                            d.showInfo();
                        } else if (a instanceof Cat) {
                            Cat c = (Cat) a;
                            c.showInfo();
                        } else if (a instanceof Elephant) {
                            Elephant e = (Elephant) a;
                            e.showInfo();
                        }
                    }
                    break;
                case 2:
                    System.out.println("--- OVERRIDING: makeSound() ---");
                    for (int i = 0; i < totalAnimal; i++) {
                        Animal a = list[i];
                        if (a instanceof Dog) {
                            Dog d = (Dog) a;
                            d.makeSound();
                        } else if (a instanceof Cat) {
                            Cat c = (Cat) a;
                            c.makeSound();
                        } else if (a instanceof Elephant) {
                            Elephant e = (Elephant) a;
                            e.makeSound();
                        }
                    }
                    break;
                case 3:
                    System.out.println("--- OVERRIDING: eat() ---");
                    for (int i = 0; i < totalAnimal; i++) {
                        Animal a = list[i];
                        if (a instanceof Dog) {
                            Dog d = (Dog) a;
                            d.eat();
                            d.eat("meat");
                        } else if (a instanceof Cat) {
                            Cat c = (Cat) a;
                            c.eat("fish");
                        } else if (a instanceof Elephant) {
                            Elephant e = (Elephant) a;
                            e.eat();
                        }
                    }
                    System.out.println("------------------------");
                    break;
                case 4:
                    System.out.println("--- POLYMOPHISM RUNTIME ---");
                    for (int i = 0; i < totalAnimal; i++) {
                        Animal a = list[i];
                        a.makeSound();
                    }
                    break;
                case 5:
                    System.out.println("--- PHƯƠNG THỨC RIÊNG CỦA TỪNG LOÀI ---");
                    for (int i = 0; i < totalAnimal; i++) {
                        Animal a = list[i];
                        if (a instanceof Dog) {
                            Dog d = (Dog) a;
                            d.fetchBall();
                        } else if (a instanceof Cat) {
                            Cat c = (Cat) a;
                            c.climbTree();
                        } else if (a instanceof Elephant) {
                            Elephant e = (Elephant) a;
                            e.sprayWater();
                        }
                    }
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}
