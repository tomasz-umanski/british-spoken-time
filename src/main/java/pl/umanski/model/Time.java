package pl.umanski.model;

/**
 * Represents a time in 12-hour format with hour and minute fields.
 * This record provides immutable time representation with built-in validation.
 *
 * @param hour   the hour field (0-12)
 * @param minute the minute field (0-59)
 */
public record Time(int hour, int minute) {

    /**
     * Constructor that validates the provided hour and minute values.
     *
     * @throws IllegalArgumentException if hour is not between 0 and 12 (inclusive)
     * @throws IllegalArgumentException if minute is not between 0 and 59 (inclusive)
     */
    public Time {
        validateHour(hour);
        validateMinute(minute);
    }

    /**
     * Determines if this time represents an exact hour (no minutes).
     */
    public boolean isExactHour() {
        return minute == 0;
    }

    /**
     * Checks if the time represents noon.
     */
    public boolean isNoon() {
        return hour == 12;
    }

    /**
     * Checks if the time represents midnight.
     */
    public boolean isMidnight() {
        return hour == 0;
    }

    /**
     * Validates that the hour is within the acceptable range.
     *
     * @param hour the hour to validate
     * @throws IllegalArgumentException if hour is not between 0 and 12 (inclusive)
     */
    private static void validateHour(int hour) {
        if (hour < 0 || hour > 12) {
            throw new IllegalArgumentException("Hour must be between 0 and 12, got: " + hour);
        }
    }

    /**
     * Validates that the minute is within the acceptable range.
     *
     * @param minute the minute to validate
     * @throws IllegalArgumentException if minute is not between 0 and 59 (inclusive)
     */
    private static void validateMinute(int minute) {
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute must be between 0 and 59, got: " + minute);
        }
    }

}
