package bt2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getStatus() {
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(startDate)) {
            return "Upcoming (Sap toi)";
        } else if (now.isAfter(endDate)) {
            return "Finished (Da ket thuc)";
        } else {
            return "Ongoing (Dang dien ra)";
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Event: " + name +
                " | Start: " + startDate.format(formatter) +
                " | End: " + endDate.format(formatter) +
                " | Status: " + getStatus();
    }
}