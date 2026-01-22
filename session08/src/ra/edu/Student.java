package ra.edu;

import java.util.Scanner;

public class Student {
    // 	id, name, dateofbirth, email, phone, sex
    private int id;
    private String name;
    private String dateOfBirth;
    private String email;
    private String phone;
    private boolean sex; // true: male, false: female

    public Student() {
    }

    public Student(int id, String name, String dateOfBirth, String email, String phone, boolean sex) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public void inputData(Scanner sc){
        System.out.println("Nhập dữ liệu sinh viên");
        System.out.println("Nhập tên sinh viên");
        name = sc.nextLine();
        System.out.println("Nhập ngày sinh : (dd/MM/yyyy)");
        dateOfBirth = sc.nextLine();
        System.out.println("Nhập email : ");
        email = sc.nextLine();
        System.out.println("Nhập số điện thoại : ");
        phone = sc.nextLine();
        System.out.println("Nhập giới tính (1. Nam , 0. Nữ) : ");
        int sexChoice = Integer.parseInt(sc.nextLine());
        sex = sexChoice == 1;
    }
    public void displayData() {
        System.out.printf("| %-4d | %-15s | %-10s | %-15s | %-10s | %-5s |\n",
                id, name, dateOfBirth, email, phone, sex ? "Nam" : "Nữ");
    }
}