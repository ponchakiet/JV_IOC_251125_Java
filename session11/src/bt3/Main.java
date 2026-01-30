package bt3;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];

        employees[0] = new FullTimeEmployee(1, "Nguyễn Văn A", 15_000_000);
        employees[1] = new PartTimeEmployee(2, "Trần Văn B", 80, 50_000);

        for (Employee emp : employees) {
            emp.showInfo();
            System.out.println("Lương: " + emp.calculateSalary());

            if (emp instanceof BonusEligible) {
                double bonus = ((BonusEligible) emp).calculateBonus();
                System.out.println("Thưởng: " + bonus);
            }

            System.out.println("----------------------");
        }
    }
}
