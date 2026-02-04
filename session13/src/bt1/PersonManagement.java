package bt1;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PersonManagement {
    List<Person> persons;

    public PersonManagement() {
        persons = new LinkedList<>();
    }

    public void addPerson(Scanner sc) {
        Person p = new Person();
        System.out.println("Nhập tên người dùng: ");
        p.setName(sc.nextLine());
        System.out.println("Nhập email người dùng: ");
        p.setEmail(sc.nextLine());
        System.out.println("Nhập số điện thoại người dùng: ");
        p.setPhone(sc.nextLine());
        persons.add(p);
    }

    public void removePerson(Scanner sc) {
        System.out.println("Nhập email người dùng muốn xóa: ");
        String email = sc.nextLine();
        for (Person p : persons) {
            if (p.getEmail().equals(email)) {
                persons.remove(p);
            }
        }
    }

    public void showAllPersons() {
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}
