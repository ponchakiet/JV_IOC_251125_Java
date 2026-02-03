package bt2;

public abstract class Asset {
    protected String assetCode;
    protected String name;
    protected double purchasePrice;

    public Asset(String assetCode, String name, double purchasePrice) {
        this.assetCode = assetCode;
        this.name = name;
        this.purchasePrice = purchasePrice;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double price) {
        this.purchasePrice = price;
    }

    public abstract double getMarketValue();

    public void display() {
        System.out.printf("Mã: %s | Tên: %s | Giá hiện tại: %.0f%n",
                assetCode, name, getMarketValue());
    }
}
