package bt02;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public static void main(String args[]) {
        Book book1 = new Book("Lập trình Java cơ bản", "Nguyễn Văn A", 1000);
        System.out.printf("Title: %s, Author: %s, Price: %.0f", book1.title, book1.author, book1.price);
    }
}
