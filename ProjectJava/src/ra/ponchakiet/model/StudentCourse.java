package ra.ponchakiet.model;

public class StudentCourse {
    private String courseName;
    private String studentName;

    public StudentCourse(String courseName, String studentName) {
        this.courseName = courseName;
        this.studentName = studentName;
    }

    public StudentCourse() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void display() {
        System.out.printf("| Học viên: %-35s |\n", studentName);
    }
}
