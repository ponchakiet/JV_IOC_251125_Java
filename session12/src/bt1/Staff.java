package bt1;

public abstract class Staff implements ICapability {
    protected int id;
    protected String name;
    protected double baseSalary;

    public Staff(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateTotalSalary();

    public void inputCommon(java.util.Scanner sc) {
        System.out.print("Nhập tên: ");
        name = sc.nextLine();

        System.out.print("Nhập lương cơ bản: ");
        baseSalary = Double.parseDouble(sc.nextLine());
    }

    public void display() {
        System.out.printf("ID: %d | Tên: %s | Lương thực nhận: %.0f%n",
                id, name, calculateTotalSalary());
    }
}
