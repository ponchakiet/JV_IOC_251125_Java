package bt3;

public class PartTimeEmployee extends Employee {
    private int workingHour;
    private double salaryPerHour;

    public PartTimeEmployee(int id, String name, int workingHour, double salaryPerHour) {
        super(id, name);
        this.workingHour = workingHour;
        this.salaryPerHour = salaryPerHour;
    }

    @Override
    public double calculateSalary() {
        return workingHour * salaryPerHour;
    }
}
