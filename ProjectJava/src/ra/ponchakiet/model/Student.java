package ra.ponchakiet.model;

import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;
import ra.ponchakiet.utils.Validate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student implements IBaseModel {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private Boolean sex;
    private String phone;
    private String password;
    private LocalDate createAt;

    public Student(String name, LocalDate dob, String email, Boolean sex, String phone, String password) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
    }

    public Student() {
    }

    public Student(int id, String name, LocalDate dob, String email, Boolean sex, String phone, String password, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập tên học viên: ");
        this.name = InputMethods.getString();
        while (true) {
            System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
            String dateStr = InputMethods.getString();
            this.dob = Validate.parseDate(dateStr);
            if (this.dob != null) {
                break;
            }
            System.out.println(Colors.RED + "Lỗi: Ngày sinh không đúng định dạng dd/MM/yyyy hoặc ngày không tồn tại!" + Colors.RESET);
        }
        while (true) {
            System.out.println("Nhập email học viên: ");
            String emailInput = InputMethods.getString();
            if (Validate.isValidEmail(emailInput)) {
                this.email = emailInput;
                break;
            }
            System.out.println(Colors.RED + "Lỗi: Email không đúng định dạng (VD: example@gmail.com)!" + Colors.RESET);
        }
        System.out.println("Chọn giới tính (1. Nam | 2. Nữ): ");
        this.sex = (InputMethods.getInteger() == 1);
        while (true) {
            System.out.println("Nhập số điện thoại (10 số, bắt đầu bằng 0): ");
            String phoneInput = InputMethods.getString();
            if (Validate.isValidPhone(phoneInput)) {
                this.phone = phoneInput;
                break;
            }
            System.out.println(Colors.RED + "Lỗi: Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0!" + Colors.RESET);
        }
        System.out.println("Nhập mật khẩu: ");
        this.password = InputMethods.getString();
    }

    @Override
    public void displayData() {
        String gioiTinh =  sex ? "Nam" : "Nữ";
        System.out.printf("| ID: %-4d | Tên: %-20s | NS: %-10s | GT: %-4s | SĐT: %-11s | Email: %-22s | Ngày tạo: %-10s |\n",
                id,
                name,
                dob.format(Validate.DATE_FORMATTER),
                gioiTinh,
                phone,
                email,
                createAt.format(Validate.DATE_FORMATTER)
        );
    }
}
