package pl.umanski;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses strings representing time in {@code HH:MM} or {@code H:MM} format into {@link Time} objects.
 * Hours must be between 0 and 12, and minutes between 0 and 59.
 */
public class TimeParser {

    /**
     * Regex pattern to match valid time strings in the format HH:MM or H:MM.
     */
    private static final Pattern TIME_PATTERN = Pattern.compile("^(\\d{1,2}):(\\d{2})$");

    private TimeParser() {
    }

    /**
     * Parses a string into a {@link Time} object.
     *
     * @param timeString the time string in HH:MM or H:MM format
     * @return the {@link Time} object
     * @throws IllegalArgumentException if the input is null, empty, malformed
     */
    public static Time parse(String timeString) {
        validateInput(timeString);

        Matcher matcher = TIME_PATTERN.matcher(timeString.trim());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid time format");
        }

        int hour = Integer.parseInt(matcher.group(1));
        int minute = Integer.parseInt(matcher.group(2));
        return new Time(hour, minute);
    }

    /**
     * Method that validates the provided string.
     *
     * @throws IllegalArgumentException if the input is null, empty, malformed
     */
    private static void validateInput(String timeString) {
        if (timeString == null) {
            throw new IllegalArgumentException("Time string cannot be null");
        }
        if (timeString.trim().isEmpty()) {
            throw new IllegalArgumentException("Time string cannot be empty");
        }
    }

}
