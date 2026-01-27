package ra.edu;

import java.util.Scanner;

public class StudentManager {
    private static Student[] students;
    private static int size;
    private static final int MAX_SIZE = 100;

    static {
        // khởi tao mảng 100 sinh viên
        students = new Student[MAX_SIZE];
        size = 0;
    }

    public static void showListStudent() {
        // kểm tra mảng ko có sinh viên
        if (size == 0) {
            System.out.println("Chưa có sinh viên nào trong danh sách");
            return;
        }
        // in tiêu đề bảng
        System.out.printf("+%s+%s+%s+%s+%s+%s+\n", "-".repeat(6), "-".repeat(17),
                "-".repeat(12), "-".repeat(17), "-".repeat(12), "-".repeat(7));
        System.out.printf("| %-4s | %-15s | %-10s | %-15s | %-10s | %-5s |\n",
                "ID", "Tên Sinh Viên", "Ngày Sinh", "Email", "Số ĐT", "Sex");
        System.out.printf("+%s+%s+%s+%s+%s+%s+\n", "-".repeat(6), "-".repeat(17),
                "-".repeat(12), "-".repeat(17), "-".repeat(12), "-".repeat(7));
        // duyệt mảng
        for (int i = 0; i < size; i++) {
            students[i].displayData();
        }
        System.out.printf("+%s+%s+%s+%s+%s+%s+\n", "-".repeat(6), "-".repeat(17),
                "-".repeat(12), "-".repeat(17), "-".repeat(12), "-".repeat(7));

    }

    public static void addStudent() {
        Scanner sc = new Scanner(System.in);
        Student student = new Student();
        student.inputData(sc);
        student.setId(getMaxId() + 1);
        students[size] = student;
        size++;
    }

    public static void updateStudent(int n) {
        Scanner sc = new Scanner(System.in);
        Student student = students[n];
        student.inputData(sc);
    }

    public static void deleteStudent(int s) {
        Scanner sc = new Scanner(System.in);
        for(int i = s; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        students[size - 1] = null;
        size--;
    }

    private static int getMaxId() {
        int maxId = 0;
        for (int i = 0; i < size; i++) {
            if (maxId < students[i].getId()) {
                maxId = students[i].getId();
            }
        }
        return maxId;
    }
}