package bt3;

public class FruitTea extends Drink {
    public FruitTea(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Cách pha: Lắc với đá và trái cây tươi");
    }
}
