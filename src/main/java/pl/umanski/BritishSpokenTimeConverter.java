package pl.umanski;

import java.util.Map;

/**
 * Converts {@link Time} objects into their British spoken representation.
 */
public class BritishSpokenTimeConverter {

    private static final String NOON = "noon";
    private static final String MIDNIGHT = "midnight";
    private static final String O_CLOCK = " o'clock";
    private static final String PAST = " past ";
    private static final String TO = " to ";

    /**
     * Mapping of hour values to their British English word equivalents.
     */
    private static final Map<Integer, String> HOUR_WORDS = Map.ofEntries(
            Map.entry(0, "twelve"),
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
     * Mapping of minute values to their British English word equivalents.
     */
    private static final Map<Integer, String> MINUTE_WORDS = Map.ofEntries(
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
            Map.entry(11, "eleven"),
            Map.entry(12, "twelve"),
            Map.entry(13, "thirteen"),
            Map.entry(14, "fourteen"),
            Map.entry(15, "quarter"),
            Map.entry(16, "sixteen"),
            Map.entry(17, "seventeen"),
            Map.entry(18, "eighteen"),
            Map.entry(19, "nineteen"),
            Map.entry(20, "twenty"),
            Map.entry(21, "twenty one"),
            Map.entry(22, "twenty two"),
            Map.entry(23, "twenty three"),
            Map.entry(24, "twenty four"),
            Map.entry(25, "twenty five"),
            Map.entry(26, "twenty six"),
            Map.entry(27, "twenty seven"),
            Map.entry(28, "twenty eight"),
            Map.entry(29, "twenty nine"),
            Map.entry(30, "half"),
            Map.entry(31, "thirty one"),
            Map.entry(32, "thirty two"),
            Map.entry(33, "thirty three"),
            Map.entry(34, "thirty four"),
            Map.entry(36, "thirty six"),
            Map.entry(37, "thirty seven"),
            Map.entry(38, "thirty eight"),
            Map.entry(39, "thirty nine")
    );

    /**
     * Converts a given {@link Time} instance to its British spoken form.
     *
     * @param time the {@link Time} to convert
     * @return the British spoken time representation
     */
    public String convert(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("time must not be null");
        }
        if (time.isExactHour()) {
            return formatExactHour(time);
        }
        if (isPastTime(time)) {
            return formatPastTime(time);
        }
        if (isDigitalTime(time)) {
            return formatDigitalTime(time);
        }
        if (isToTime(time)) {
            return formatToTime(time);
        }
        return "";
    }

    /**
     * Checks if the time falls in the 'to' range (minutes 40-59 and 35).
     */
    private boolean isToTime(Time time) {
        return time.minute() >= 40 && time.minute() <= 59 || time.minute() == 35;
    }

    /**
     * Formats a time in the "to" range (minutes 40-59 and 35)
     */
    private String formatToTime(Time time) {
        int minutesTo = 60 - time.minute();
        int nextHour = (time.hour() + 1) % 12;

        String minuteWord = getMinuteWord(minutesTo);
        String nextHourWord = getHourWord(nextHour);

        return minuteWord + TO + nextHourWord;
    }

    /**
     * Checks if the time falls in the digital range (minutes 31-39 except 35).
     */
    private boolean isDigitalTime(Time time) {
        return time.minute() >= 31 && time.minute() <= 39 && time.minute() != 35;
    }

    /**
     * Formats a time to digital form (minutes 31-39 except 35)
     */
    private String formatDigitalTime(Time time) {
        String minuteWord = getMinuteWord(time.minute());
        String hourWord = getDisplayHourWord(time);
        return hourWord + " " + minuteWord;
    }

    /**
     * Formats an exact hour (minute = 0) to its spoken form.
     */
    private String formatExactHour(Time time) {
        if (isNoon(time)) return NOON;
        if (isMidnight(time)) return MIDNIGHT;
        return getHourWord(time.hour()) + O_CLOCK;
    }

    /**
     * Checks if the time falls in the "past" range (minutes 1–30).
     */
    private boolean isPastTime(Time time) {
        return time.minute() >= 1 && time.minute() <= 30;
    }

    /**
     * Formats a time in the "past" range (minutes 1–30) to its spoken form.
     */
    private String formatPastTime(Time time) {
        String minuteWord = getMinuteWord(time.minute());
        String hourWord = getDisplayHourWord(time);
        return minuteWord + PAST + hourWord;
    }

    /**
     * Returns the display hour word, handling special cases for noon and midnight.
     */
    private String getDisplayHourWord(Time time) {
        if (isNoon(time)) return NOON;
        if (isMidnight(time)) return MIDNIGHT;
        return getHourWord(time.hour());
    }

    /**
     * Returns the spoken word for an hour.
     */
    private String getHourWord(int hour) {
        return HOUR_WORDS.get(hour);
    }

    /**
     * Returns the spoken word for a minute.
     */
    private String getMinuteWord(int minute) {
        return MINUTE_WORDS.get(minute);
    }

    /**
     * Checks if the given hour represents noon.
     */
    private boolean isNoon(Time time) {
        return time.hour() == 12;
    }

    /**
     * Checks if the given hour represents midnight.
     */
    private boolean isMidnight(Time time) {
        return time.hour() == 0;
    }

}
