package bt3;

public abstract class Drink implements IPromotion {
    private int id;
    private String name;
    private double price;

    public Drink(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // getter / setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // interface
    @Override
    public void applyDiscount(double percentage) {
        price = price * (1 - percentage / 100);
    }

    public abstract void prepare();

    public void display() {
        System.out.printf("ID: %d | Tên: %s | Giá: %.0f | ",
                id, name, price);
        prepare();
    }
}
