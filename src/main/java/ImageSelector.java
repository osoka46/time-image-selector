import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ImageSelector {

    public String selectImage(String timeString) {
        try {
            // convert parameter to time object (example: "14:30")
            LocalTime time = LocalTime.parse(timeString);

            // rule 1: 07:00 - 11:59 -> morning.jpg
            if (isBetween(time, 7, 0, 11, 59)) {
                return "morning.jpg";
            }
            // rule 2: 12:00 - 13:59 -> noon.jpg
            else if (isBetween(time, 12, 0, 13, 59)) {
                return "noon.jpg";
            }
            // rule 3: Kural: 14:00 - 17:59 -> afternoon.jpg
            else if (isBetween(time, 14, 0, 17, 59)) {
                return "afternoon.jpg";
            }
            // rule 4: 18:00 - 21:59 -> evening.jpg
            else if (isBetween(time, 18, 0, 21, 59)) {
                return "evening.jpg";
            }
            // rule 5: rest (22:00 - 06:59) -> night.jpg
            else {
                return "night.jpg";
            }
        } catch (DateTimeParseException | NullPointerException e) {
            return "broken_watch.jpg";
        }
    }

    private boolean isBetween(LocalTime time, int startHour, int startMin, int endHour, int endMin) {
        LocalTime start = LocalTime.of(startHour, startMin);
        LocalTime end = LocalTime.of(endHour, endMin);
        // time >= start AND time <= end
        return !time.isBefore(start) && !time.isAfter(end);
    }
}