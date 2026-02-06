package bt2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final SubjectManager<Subject> manager = new SubjectManager<>();
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        seedData(); // dữ liệu mẫu để test nhanh

        while (true) {
            printMenu();
            int choice = readInt("Chọn chức năng: ");

            switch (choice) {
                case 1 -> showSubjects();
                case 2 -> addSubject();
                case 3 -> deleteSubjectByCode();
                case 4 -> searchByName();
                case 5 -> filterCreditsGreaterThan3();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== QUẢN LÝ MÔN HỌC =====");
        System.out.println("1. Hiển thị danh sách môn học");
        System.out.println("2. Thêm môn học");
        System.out.println("3. Xóa môn học theo code");
        System.out.println("4. Tìm kiếm môn học theo tên");
        System.out.println("5. Lọc môn học có credits > 3");
        System.out.println("0. Thoát");
        System.out.println("===========================");
    }

    // ===== CHỨC NĂNG =====

    private static void showSubjects() {
        System.out.println("\n--- DANH SÁCH MÔN HỌC ---");
        List<Subject> list = manager.getAll();
        if (list.isEmpty()) {
            System.out.println("(Danh sách trống)");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static void addSubject() {
        System.out.println("\n--- THÊM MÔN HỌC ---");
        String code;

        while (true) {
            code = readNonEmpty("Nhập code: ");
            boolean existed = manager.findFirst(s -> ((Subject) s).getCode().equalsIgnoreCase(code)).isPresent();
            if (existed) System.out.println("Lỗi: Code đã tồn tại. Nhập code khác!");
            else break;
        }

        String name = readNonEmpty("Nhập tên môn: ");
        int credits = readCredits("Nhập số tín chỉ (0..10): ");
        LocalDate startDate = readDate("Nhập ngày bắt đầu (yyyy-MM-dd): ");

        manager.add(new Subject(code, name, credits, startDate));
        System.out.println("✅ Thêm môn học thành công!");
    }

    private static void deleteSubjectByCode() {
        System.out.println("\n--- XÓA MÔN HỌC ---");
        String code = readNonEmpty("Nhập code cần xóa: ");

        boolean removed = manager.removeIf(s -> ((Subject) s).getCode().equalsIgnoreCase(code));
        if (removed) System.out.println("✅ Xóa thành công!");
        else System.out.println("❌ Lỗi: Không tìm thấy môn học với code = " + code);
    }

    private static void searchByName() {
        System.out.println("\n--- TÌM KIẾM THEO TÊN (Stream + Optional) ---");
        String keyword = readNonEmpty("Nhập tên môn cần tìm: ").toLowerCase();

        Optional<Subject> found = manager
                .findFirst(s -> ((Subject) s).getName().toLowerCase().contains(keyword))
                .map(s -> (Subject) s);

        if (found.isPresent()) {
            System.out.println("✅ Tìm thấy: " + found.get());
        } else {
            System.out.println("Không có môn học phù hợp");
        }
    }

    private static void filterCreditsGreaterThan3() {
        System.out.println("\n--- MÔN HỌC CÓ CREDITS > 3 ---");
        List<Subject> result = manager
                .filter(s -> ((Subject) s).getCredits() > 3)
                .stream().map(s -> (Subject) s).toList();

        if (result.isEmpty()) {
            System.out.println("Không có môn nào credits > 3");
        } else {
            result.forEach(s -> System.out.println("- " + s));
        }
    }

    // ===== NHẬP LIỆU + EXCEPTION HANDLING =====

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    private static int readCredits(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();

            try {
                int credits = Integer.parseInt(s.trim());
                validateCredits(credits); // ném exception nếu sai
                return credits;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Tín chỉ phải là số nguyên!");
            } catch (InvalidCreditsException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }

    private static void validateCredits(int credits) throws InvalidCreditsException {
        if (credits < 0 || credits > 10) {
            throw new InvalidCreditsException("Số tín chỉ không hợp lệ (phải trong khoảng 0..10).");
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                return LocalDate.parse(s.trim(), F); // DateTime API + Formatter
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Sai định dạng ngày. Đúng dạng yyyy-MM-dd (ví dụ 2026-02-06).");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            if (s == null || s.trim().isEmpty()) {
                System.out.println("Lỗi: Không được để trống!");
            } else return s.trim();
        }
    }

    // ===== DỮ LIỆU MẪU =====
    private static void seedData() {
        manager.add(new Subject("CTDL", "Cấu trúc dữ liệu", 4, LocalDate.parse("2026-02-10", F)));
        manager.add(new Subject("KTDL", "Khai thác dữ liệu", 3, LocalDate.parse("2026-02-15", F)));
        manager.add(new Subject("CNPM", "Công nghệ phần mềm", 3, LocalDate.parse("2026-03-01", F)));
    }
}
