package bt2;

import java.util.ArrayList;

public class AttendanceManager implements Manage<Student>{
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    public void add(Student item) {
        students.add(item);
        System.out.println("✔ Đã thêm sinh viên");
    }

    @Override
    public void update(int index, Student item) {
        if (index >= 0 && index < students.size()) {
            students.set(index, item);
            System.out.println("✔ Đã cập nhật sinh viên");
        } else {
            System.out.println("✖ Vị trí không hợp lệ");
        }
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            System.out.println("✔ Đã xóa sinh viên");
        } else {
            System.out.println("✖ Vị trí không hợp lệ");
        }
    }

    @Override
    public void display() {
        if (students.isEmpty()) {
            System.out.println("Danh sách điểm danh trống");
            return;
        }

        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i + ". " + students.get(i));
        }
    }
}
