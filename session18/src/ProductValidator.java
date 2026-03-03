import java.time.LocalDate;

public class ProductValidator {
    public static void validate(String name, Double price, String title, LocalDate created, String catalog) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product_Name không được rỗng.");
        if (name.trim().length() > 100)
            throw new IllegalArgumentException("Product_Name tối đa 100 ký tự.");

        if (price == null || price <= 0)
            throw new IllegalArgumentException("Product_Price phải > 0.");

        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Product_Title không được rỗng.");
        if (title.trim().length() > 200)
            throw new IllegalArgumentException("Product_Title tối đa 200 ký tự.");

        if (created == null)
            throw new IllegalArgumentException("Product_created không được rỗng.");

        if (catalog == null || catalog.trim().isEmpty())
            throw new IllegalArgumentException("Product_catalog không được rỗng.");
        if (catalog.trim().length() > 100)
            throw new IllegalArgumentException("Product_catalog tối đa 100 ký tự.");
    }
}