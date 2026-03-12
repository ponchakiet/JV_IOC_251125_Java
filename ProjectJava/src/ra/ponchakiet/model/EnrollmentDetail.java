package ra.ponchakiet.model;

import ra.ponchakiet.utils.Validate;

import java.time.LocalDate;

public class EnrollmentDetail {
    private int id;
    private String studentName;
    private String courseName;
    private LocalDate registeredAt;
    private String status;

    public EnrollmentDetail() {}

    public EnrollmentDetail(int id, String studentName, String courseName, LocalDate registeredAt, String status) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayData() {
        System.out.printf("| ID: %-5d | Học Viên: %-30s | Khóa Học: %-40s | Ngày: %-15s | Trạng thái: %-15s |\n",
                id,
                studentName,
                courseName,
                registeredAt.format(Validate.DATE_FORMATTER),
                status
        );
    }
}
