import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class bt4 {

    // Custom Exception: sai độ dài
    static class InvalidPhoneNumberLengthException extends Exception {
        public InvalidPhoneNumberLengthException(String message) {
            super(message);
        }
    }

    // Custom Exception: chứa ký tự không hợp lệ (không phải 0-9)
    static class InvalidPhoneNumberCharacterException extends Exception {
        public InvalidPhoneNumberCharacterException(String message) {
            super(message);
        }
    }

    // Hàm kiểm tra SĐT và ném exception phù hợp
    static void validatePhone(String phone)
            throws InvalidPhoneNumberLengthException, InvalidPhoneNumberCharacterException {

        // Quy tắc: không được có khoảng trắng
        if (phone.contains(" ")) {
            throw new InvalidPhoneNumberCharacterException("Chứa khoảng trắng (không hợp lệ)");
        }

        // Quy tắc: đúng 10 ký tự
        if (phone.length() != 10) {
            throw new InvalidPhoneNumberLengthException("Sai độ dài (phải đúng 10 chữ số)");
        }

        // Quy tắc: chỉ chứa chữ số 0-9
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (c < '0' || c > '9') {
                throw new InvalidPhoneNumberCharacterException("Chứa ký tự không phải số: '" + c + "'");
            }
        }
    }

    // Lưu số + lý do lỗi
    static class InvalidItem {
        String value;
        String reason;

        InvalidItem(String value, String reason) {
            this.value = value;
            this.reason = reason;
        }

        @Override
        public String toString() {
            return value + " -> " + reason;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> validPhones = new ArrayList<>();
        List<InvalidItem> invalidPhones = new ArrayList<>();

        System.out.println("Nhập danh sách số điện thoại (cách nhau bởi dấu phẩy ,)");
        System.out.println("Ví dụ: 0912345678,0987654321,01234abcde");
        System.out.print("Input: ");

        String input = sc.nextLine();

        // Tách theo dấu phẩy
        String[] parts = input.split(",");

        for (String raw : parts) {
            String phone = raw; // giữ nguyên để bắt lỗi khoảng trắng
            // Nếu bạn muốn cho phép người dùng lỡ gõ khoảng trắng quanh dấu phẩy,
            // có thể dùng phone = raw.trim(); nhưng đề bài nói "không chứa khoảng trắng"
            // nên ở đây ta giữ nguyên để phát hiện.

            try {
                validatePhone(phone);
                validPhones.add(phone);
            } catch (InvalidPhoneNumberLengthException e) {
                invalidPhones.add(new InvalidItem(phone, e.getMessage()));
            } catch (InvalidPhoneNumberCharacterException e) {
                invalidPhones.add(new InvalidItem(phone, e.getMessage()));
            }
        }

        System.out.println("\n===== KẾT QUẢ =====");

        System.out.println("Danh sách SĐT hợp lệ (" + validPhones.size() + "):");
        if (validPhones.isEmpty()) {
            System.out.println("(Không có)");
        } else {
            for (String p : validPhones) System.out.println("- " + p);
        }

        System.out.println("\nDanh sách SĐT không hợp lệ (" + invalidPhones.size() + "):");
        if (invalidPhones.isEmpty()) {
            System.out.println("(Không có)");
        } else {
            for (InvalidItem item : invalidPhones) System.out.println("- " + item);
        }

        sc.close();
    }
}
