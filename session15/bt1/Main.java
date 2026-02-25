package bt1;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final MovieManager<Movie> manager = new MovieManager<>();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Chọn chức năng: ");

            switch (choice) {
                case 1: 
                    addMovie();
                    break;
                case 2: 
                    editMovie();
                    break;
                case 3:
                    deleteMovie();
                    break;
                case 4:
                    showMovies();
                    break;
                case 5:
                    searchByTitle();
                    break;
                case 6:
                    filterByRating();
                    break;
                case 0: {
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    sc.close();
                    return;
                }
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
                    break;
            }
        }
    }

    // ===== MENU =====
    private static void printMenu() {
        System.out.println("\n========== QUẢN LÝ PHIM ==========");
        System.out.println("1. Thêm phim mới");
        System.out.println("2. Sửa phim theo id");
        System.out.println("3. Xóa phim theo id");
        System.out.println("4. Hiển thị danh sách phim");
        System.out.println("5. Tìm kiếm phim theo tên");
        System.out.println("6. Lọc phim có rating > 8.0");
        System.out.println("0. Thoát");
        System.out.println("==================================");
    }

    // ===== CRUD + SEARCH + FILTER =====

    private static void addMovie() {
        System.out.println("\n--- THÊM PHIM MỚI ---");

        String id;
        while (true) {
            id = readNonEmpty("Nhập id: ");
            Movie existed = manager.findFirst(m -> m.getId().equalsIgnoreCase(id));
            if (existed != null) {
                System.out.println("Lỗi: ID đã tồn tại! Nhập ID khác.");
            } else break;
        }

        String title = readNonEmpty("Nhập tên phim: ");
        String director = readNonEmpty("Nhập đạo diễn: ");
        LocalDate releaseDate = readDate("Nhập ngày phát hành (yyyy-MM-dd): ");
        double rating = readDouble("Nhập rating (0.0 - 10.0): ", 0.0, 10.0);

        manager.add(new Movie(id, title, director, releaseDate, rating));
        System.out.println("✅ Thêm phim thành công!");
    }

    private static void editMovie() {
        System.out.println("\n--- SỬA PHIM ---");
        String id = readNonEmpty("Nhập id phim cần sửa: ");

        Movie movie = manager.findFirst(m -> m.getId().equalsIgnoreCase(id));
        if (movie == null) {
            System.out.println("Không tìm thấy phim với id = " + id);
            return;
        }

        System.out.println("Phim hiện tại: " + movie);
        System.out.println("Nhập thông tin mới (để trống nếu muốn giữ nguyên):");

        String newTitle = readLine("Tên phim mới: ");
        if (!newTitle.isBlank()) movie.setTitle(newTitle.trim());

        String newDirector = readLine("Đạo diễn mới: ");
        if (!newDirector.isBlank()) movie.setDirector(newDirector.trim());

        String newDateStr = readLine("Ngày phát hành mới (yyyy-MM-dd): ");
        if (!newDateStr.isBlank()) {
            try {
                movie.setReleaseDate(LocalDate.parse(newDateStr.trim()));
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Sai định dạng ngày. Giữ nguyên ngày cũ!");
            }
        }

        String newRatingStr = readLine("Rating mới (0.0 - 10.0): ");
        if (!newRatingStr.isBlank()) {
            try {
                double r = Double.parseDouble(newRatingStr.trim());
                if (r < 0.0 || r > 10.0) {
                    System.out.println("Lỗi: Rating phải trong [0.0, 10.0]. Giữ nguyên rating cũ!");
                } else {
                    movie.setRating(r);
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Rating phải là số. Giữ nguyên rating cũ!");
            }
        }

        System.out.println("✅ Sửa phim thành công!");
        System.out.println("Phim sau khi sửa: " + movie);
    }

    private static void deleteMovie() {
        System.out.println("\n--- XÓA PHIM ---");
        String id = readNonEmpty("Nhập id phim cần xóa: ");

        boolean removed = manager.removeIf(m -> m.getId().equalsIgnoreCase(id));
        if (removed) System.out.println("✅ Xóa phim thành công!");
        else System.out.println("Không tìm thấy phim để xóa với id = " + id);
    }

    private static void showMovies() {
        System.out.println("\n--- DANH SÁCH PHIM ---");
        List<Movie> movies = manager.getAll();
        if (movies.isEmpty()) {
            System.out.println("(Danh sách trống)");
            return;
        }
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
    }

    private static void searchByTitle() {
        System.out.println("\n--- TÌM KIẾM THEO TÊN ---");
        String keyword = readNonEmpty("Nhập tên phim cần tìm: ").toLowerCase();

        List<Movie> found = manager.filter(m -> m.getTitle().toLowerCase().contains(keyword));
        if (found.isEmpty()) {
            System.out.println("Không tìm thấy phim");
        } else {
            System.out.println("Kết quả tìm thấy (" + found.size() + "):");
            for (Movie m : found) System.out.println("- " + m);
        }
    }

    private static void filterByRating() {
        System.out.println("\n--- PHIM CÓ RATING > 8.0 ---");
        List<Movie> filtered = manager.filter(m -> m.getRating() > 8.0);

        if (filtered.isEmpty()) {
            System.out.println("Không có phim nào có rating > 8.0");
        } else {
            for (Movie m : filtered) System.out.println("- " + m);
        }
    }

    // ===== HÀM NHẬP LIỆU AN TOÀN (try/catch) =====

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

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                double val = Double.parseDouble(s.trim());
                if (val < min || val > max) {
                    System.out.println("Lỗi: Giá trị phải nằm trong [" + min + ", " + max + "]");
                } else return val;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine();
            try {
                return LocalDate.parse(s.trim());
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

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
