package ra.ponchakiet.model;

import ra.ponchakiet.utils.InputMethods;
import ra.ponchakiet.utils.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Course implements IBaseModel{
    private int id;
    private String name;
    private int duration;
    private String instructor;
    private LocalDate createAt;

    public Course() {
    }

    public Course(String name, int duration, String instructor) {
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
    }

    public Course(int id, String name, int duration, String instructor, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public void inputData() {
        System.out.print("Nhập tên khóa học: ");
        name = InputMethods.getString();
        System.out.print("Nhập thời lượng khóa học: ");
        duration = InputMethods.getInteger();
        System.out.print("Nhập giáo viên khóa học: ");
        instructor = InputMethods.getString();
    }

    @Override
    public void displayData() {
        System.out.printf("| ID: %-5d | Tên: %-35s | Thời lượng: %-3d giờ | GV: %-25s | Ngày tạo: %-12s |\n",
                id,
                name,
                duration,
                instructor,
                createAt.format(Validate.DATE_FORMATTER)
        );
    }
}
