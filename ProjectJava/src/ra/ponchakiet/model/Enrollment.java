package ra.ponchakiet.model;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Enrollment implements IBaseModel {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDateTime registeredAt;
    private EnrollmentStatus status; // 'WAITING', 'DENIED', 'CANCEL', 'CONFIRM'

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, EnrollmentStatus status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {

    }

    @Override
    public void displayData() {

    }
}
