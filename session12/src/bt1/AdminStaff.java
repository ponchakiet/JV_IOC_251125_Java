package bt1;

public class AdminStaff extends Staff {
    private double bonus;

    public AdminStaff(int id) {
        super(id, "", 0);
    }

    @Override
    public double calculateTotalSalary() {
        return baseSalary + bonus;
    }

    @Override
    public void checkPerformance() {
        System.out.println("Nhân viên hành chính được đánh giá qua KPI.");
    }

    public void input(java.util.Scanner sc) {
        inputCommon(sc);
        System.out.print("Nhập tiền thưởng: ");
        bonus = Double.parseDouble(sc.nextLine());
    }

    @Override
    public void display() {
        System.out.print("[Hành chính] ");
        super.display();
    }
}
