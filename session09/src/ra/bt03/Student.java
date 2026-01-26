package ra.bt03;

public class Student {
    private String id;
    private String fullName;
    private int age;
    private double gpa;
    private static int count;
    private final double MIN_GPA = 0.0;
    private final double MAX_GPA = 4.0;

    public Student(String id, String fullName, int age, double gpa) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        if (gpa < MIN_GPA) {
            this.gpa = MIN_GPA;
        } else if (gpa > MAX_GPA) {
            this.gpa = MAX_GPA;
        } else {
            this.gpa = gpa;
        }
        count++;
    }

    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Full name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
    }

    public static void getCount() {
        System.out.println("Student count: " + count);
    }
}
