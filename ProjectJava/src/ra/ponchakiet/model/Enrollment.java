package ra.ponchakiet.model;

import ra.ponchakiet.utils.InputMethods;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Enrollment implements IBaseModel {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDateTime registeredAt;
    private EnrollmentStatus status;

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, EnrollmentStatus status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public void inputData() {
        System.out.print("Nhập ID khóa học: ");
        this.courseId = InputMethods.getInteger();
    }

    @Override
    public void displayData() {

    }
}
