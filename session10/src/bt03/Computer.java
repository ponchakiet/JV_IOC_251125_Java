package bt03;

public class Computer {
    private int quantity;
    private double basePrice;
    private double tax;
    private double discount;

    public Computer(int quantity,double basePrice, double tax, double discount) {
        this.quantity = quantity;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
    }

    double calculatePrice(double basePrice) {
        return basePrice*this.quantity;
    }
    double calculatePrice(double basePrice, double tax) {
        return basePrice*this.quantity + basePrice*this.quantity*tax;
    }
    double calculatePrice(double basePrice, double tax, double discount) {
        return basePrice*this.quantity + basePrice*this.quantity*tax - discount;
    }
}
