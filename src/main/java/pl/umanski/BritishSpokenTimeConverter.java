package pl.umanski;

import java.util.Map;

/**
 * Converts {@link Time} objects into their British spoken representation.
 */
public class BritishSpokenTimeConverter {

    /**
     * Mapping of hour values to their British English word equivalents.
     */
    private static final Map<Integer, String> HOUR_WORDS = Map.ofEntries(
            Map.entry(1, "one"),
            Map.entry(2, "two"),
            Map.entry(3, "three"),
            Map.entry(4, "four"),
            Map.entry(5, "five"),
            Map.entry(6, "six"),
            Map.entry(7, "seven"),
            Map.entry(8, "eight"),
            Map.entry(9, "nine"),
            Map.entry(10, "ten"),
            Map.entry(11, "eleven")
    );

    /**
     * Converts a given {@link Time} instance to its British spoken form.
     *
     * @param time the {@link Time} to convert
     * @return the British spoken time representation
     * @throws IllegalArgumentException if time is null
     */
    public String convert(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("time must not be null");
        }

        return convertExactHour(time);
    }

    /**
     * Converts an exact hour to its spoken form.
     *
     * @param time the {@link Time} to convert
     * @return the spoken form, e.g. "three o'clock"
     */
    private String convertExactHour(Time time) {
        if (isNoon(time)) {
            return "noon";
        }

        if (isMidnight(time)) {
            return "midnight";
        }

        return HOUR_WORDS.get(time.hour()) + " o'clock";
    }

    /**
     * Checks if the given time represents noon (12:00).
     *
     * @param time the {@link Time} to check
     * @return true if the time is noon, false otherwise
     */
    private boolean isNoon(Time time) {
        return time.hour() == 12 && time.minute() == 0;
    }

    /**
     * Checks if the given time represents midnight (00:00).
     *
     * @param time the {@link Time} to check
     * @return true if the time is midnight, false otherwise
     */
    private boolean isMidnight(Time time) {
        return time.hour() == 0 && time.minute() == 0;
    }

}
