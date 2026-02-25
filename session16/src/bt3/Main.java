package bt3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static List<Message> messages = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== CHAT APPLICATION =====");
            System.out.println("1. Send message");
            System.out.println("2. View chat history");
            System.out.println("3. Filter by sender");
            System.out.println("4. Filter by date");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sendMessage();
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    filterBySender();
                    break;
                case 4:
                    filterByDate();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Gửi tin nhắn
    public static void sendMessage() {
        System.out.print("Enter sender name: ");
        String sender = scanner.nextLine();

        System.out.print("Enter content: ");
        String content = scanner.nextLine();

        Message message = new Message(
                sender,
                content,
                LocalDateTime.now()
        );

        messages.add(message);
        System.out.println("Message sent!");
    }

    // Xem toàn bộ lịch sử
    public static void showHistory() {
        if (messages.isEmpty()) {
            System.out.println("No messages yet!");
            return;
        }

        messages.forEach(System.out::println);
    }

    // Lọc theo người gửi
    public static void filterBySender() {
        System.out.print("Enter sender name: ");
        String sender = scanner.nextLine();

        List<Message> filtered = messages.stream()
                .filter(m -> m.getSender().equalsIgnoreCase(sender))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No messages from this sender!");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    // Lọc theo ngày
    public static void filterByDate() {
        try {
            System.out.print("Enter date (dd/MM/yyyy): ");
            String inputDate = scanner.nextLine();

            LocalDate date = LocalDate.parse(inputDate, dateFormatter);

            List<Message> filtered = messages.stream()
                    .filter(m -> m.getTimestamp().toLocalDate().equals(date))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("No messages on this date!");
            } else {
                filtered.forEach(System.out::println);
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use dd/MM/yyyy");
        }
    }
}