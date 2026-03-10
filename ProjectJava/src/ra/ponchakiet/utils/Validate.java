package ra.ponchakiet.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validate {
    private static final String PHONE_REGEX = "^0\\d{9}$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean isValidPhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
