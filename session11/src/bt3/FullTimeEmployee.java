package bt3;

public class FullTimeEmployee extends Employee implements BonusEligible {
    private double basicSalary;

    public FullTimeEmployee(int id, String name, double basicSalary) {
        super(id, name);
        this.basicSalary = basicSalary;
    }

    @Override
    public double calculateSalary() {
        return basicSalary;
    }

    @Override
    public double calculateBonus() {
        return basicSalary * 0.1;
    }
}
