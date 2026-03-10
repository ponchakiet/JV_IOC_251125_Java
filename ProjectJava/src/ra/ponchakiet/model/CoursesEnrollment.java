package ra.ponchakiet.model;

import ra.ponchakiet.utils.Validate;

import java.time.LocalDate;

public class CoursesEnrollment {
    private int courseId;
    private String courseName;
    private String instructor;
    private String status;
    private LocalDate enrollDate;

    public CoursesEnrollment(int courseId, String courseName, String instructor, String status, LocalDate enrollDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
        this.status = status;
        this.enrollDate = enrollDate;
    }

    public CoursesEnrollment() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public void displayData() {
        System.out.printf("| ID: %-5d | Môn: %-30s | Giáo Viên: %-25s | Ngày Đăng Ký: %-10s | Trạng thái: %-10s |\n",
                courseId, courseName, instructor, enrollDate.format(Validate.DATE_FORMATTER), status);
    }
}
