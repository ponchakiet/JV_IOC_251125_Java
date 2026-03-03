import java.time.LocalDate;

public class Product {
    public int id;
    public String name;
    public double price;
    public String title;
    public LocalDate created;
    public String catalog;
    public boolean status;

    @Override
    public String toString() {
        return String.format(
                "ID=%d | Name=%s | Price=%.0f | Title=%s | Created=%s | Catalog=%s | Status=%s",
                id, name, price, title, created, catalog, status
        );
    }
}