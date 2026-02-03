package bt2;

public class Computer extends Asset {
    private int ram;
    private String cpu;

    public Computer(String assetCode, String name, double purchasePrice,
                    int ram, String cpu) {
        super(assetCode, name, purchasePrice);
        this.ram = ram;
        this.cpu = cpu;
    }

    @Override
    public double getMarketValue() {
        return purchasePrice * 0.8; // khấu hao 20%
    }

    @Override
    public void display() {
        System.out.print("[Máy tính] ");
        super.display();
    }
}
