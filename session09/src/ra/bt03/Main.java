package ra.bt03;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("0001", "Kiet", 18, 4.5);
        students[1] = new Student("0002", "Kiet", 18, 4.5);
        students[2] = new Student("0003", "Kiet", 18, -2.5);
        for (Student student : students) {
            student.printInfo();
        }
        Student.getCount();
    }
}
