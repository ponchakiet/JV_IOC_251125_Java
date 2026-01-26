package ra.bt02;

public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("kiet", "kiet", 3000);
        books[1] = new Book("kiet", "kiet", 3000);
        books[2] = new Book("kiet", "kiet", 3000);
        System.out.printf("%5s LIST OF BOOKS %5s\n", "-".repeat(5), "-".repeat(5));
        for (Book book : books) {
            book.printInfo();
            System.out.println();
        }
    }
}
