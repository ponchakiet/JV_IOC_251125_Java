package bt1;

public class Lecturer extends Staff {
    private int teachingHours;

    public Lecturer(int id) {
        super(id, "", 0);
    }

    @Override
    public double calculateTotalSalary() {
        return baseSalary + teachingHours * 200000;
    }

    @Override
    public void checkPerformance() {
        System.out.println("Giảng viên được đánh giá qua số giờ giảng dạy.");
    }

    public void input(java.util.Scanner sc) {
        inputCommon(sc);
        System.out.print("Nhập số giờ giảng: ");
        teachingHours = Integer.parseInt(sc.nextLine());
    }

    @Override
    public void display() {
        System.out.print("[Giảng viên] ");
        super.display();
    }

}
