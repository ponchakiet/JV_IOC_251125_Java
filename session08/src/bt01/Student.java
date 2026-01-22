package bt01;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static void main(String args[]) {
        Student student = new Student(1, "Kiệt", 20);
        System.out.printf("ID: %-2d, Name: %-4s, Age: %-2d", student.id, student.name, student.age);
    }
}
