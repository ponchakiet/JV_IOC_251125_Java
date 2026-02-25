package bt2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Event> eventList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== EVENT MANAGEMENT =====");
            System.out.println("1. Add event");
            System.out.println("2. Show events");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    showEvents();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Thêm sự kiện
    public static void addEvent() {
        try {
            System.out.print("Enter event name: ");
            String name = scanner.nextLine();

            System.out.print("Enter start date (dd/MM/yyyy HH:mm): ");
            String startInput = scanner.nextLine();
            LocalDateTime startDate = LocalDateTime.parse(startInput, formatter);

            System.out.print("Enter end date (dd/MM/yyyy HH:mm): ");
            String endInput = scanner.nextLine();
            LocalDateTime endDate = LocalDateTime.parse(endInput, formatter);

            if (endDate.isBefore(startDate)) {
                System.out.println("End date must be after start date!");
                return;
            }

            Event event = new Event(name, startDate, endDate);
            eventList.add(event);

            System.out.println("Event added successfully!");

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use dd/MM/yyyy HH:mm");
        } catch (Exception e) {
            System.out.println("Unexpected error occurred!");
        }
    }

    // Hiển thị sự kiện
    public static void showEvents() {
        if (eventList.isEmpty()) {
            System.out.println("No events available!");
            return;
        }

        for (Event event : eventList) {
            System.out.println(event);
        }
    }
}