package bt1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MovieManagement manager = new MovieManagement();

        while (true) {
            System.out.println("\n===== MOVIE MANAGEMENT =====");
            System.out.println("1. Add movie");
            System.out.println("2. List movies");
            System.out.println("3. Update movie");
            System.out.println("4. Delete movie");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Director: ");
                        String director = scanner.nextLine();

                        System.out.print("Year: ");
                        int year = Integer.parseInt(scanner.nextLine());

                        if (title.isEmpty() || director.isEmpty()) {
                            throw new IllegalArgumentException("Title/Director cannot be empty");
                        }

                        manager.addMovie(title, director, year);
                        break;

                    case "2":
                        manager.listMovies();
                        break;

                    case "3":
                        System.out.print("ID: ");
                        int idUpdate = Integer.parseInt(scanner.nextLine());

                        System.out.print("New Title: ");
                        String newTitle = scanner.nextLine();

                        System.out.print("New Director: ");
                        String newDirector = scanner.nextLine();

                        System.out.print("New Year: ");
                        int newYear = Integer.parseInt(scanner.nextLine());

                        manager.updateMovie(idUpdate, newTitle, newDirector, newYear);
                        break;

                    case "4":
                        System.out.print("ID: ");
                        int idDelete = Integer.parseInt(scanner.nextLine());
                        manager.deleteMovie(idDelete);
                        break;

                    case "0":
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Year and ID must be numbers!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
