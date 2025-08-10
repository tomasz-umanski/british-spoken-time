package pl.umanski.vocabulary;

import pl.umanski.model.Time;

import java.util.Map;

/**
 * Utility class providing British vocabulary.
 */
public class BritishTimeVocabulary {

    /**
     * British English word for noon.
     */
    public static final String NOON = "noon";

    /**
     * British English word for midnight.
     */
    public static final String MIDNIGHT = "midnight";

    /**
     * Suffix used for exact hours in British English.
     */
    public static final String EXACT_HOUR_SUFFIX = "o'clock";

    /**
     * Preposition used for times in the "past" format.
     */
    public static final String PAST_PREPOSITION = "past";

    /**
     * Preposition used for times in the "to" format.
     */
    public static final String TO_PREPOSITION = "to";

    /**
     * Mapping of hour values to their British English word equivalents.
     */
    private static final Map<Integer, String> HOUR_WORDS = Map.ofEntries(
            Map.entry(0, "twelve"), Map.entry(1, "one"), Map.entry(2, "two"), Map.entry(3, "three"),
            Map.entry(4, "four"), Map.entry(5, "five"), Map.entry(6, "six"), Map.entry(7, "seven"),
            Map.entry(8, "eight"), Map.entry(9, "nine"), Map.entry(10, "ten"), Map.entry(11, "eleven")
    );

    /**
     * Mapping of minute values to their British English word equivalents.
     */
    private static final Map<Integer, String> MINUTE_WORDS = Map.ofEntries(
            Map.entry(1, "one"), Map.entry(2, "two"), Map.entry(3, "three"), Map.entry(4, "four"),
            Map.entry(5, "five"), Map.entry(6, "six"), Map.entry(7, "seven"), Map.entry(8, "eight"),
            Map.entry(9, "nine"), Map.entry(10, "ten"), Map.entry(11, "eleven"), Map.entry(12, "twelve"),
            Map.entry(13, "thirteen"), Map.entry(14, "fourteen"), Map.entry(15, "quarter"), Map.entry(16, "sixteen"),
            Map.entry(17, "seventeen"), Map.entry(18, "eighteen"), Map.entry(19, "nineteen"), Map.entry(20, "twenty"),
            Map.entry(21, "twenty one"), Map.entry(22, "twenty two"), Map.entry(23, "twenty three"), Map.entry(24, "twenty four"),
            Map.entry(25, "twenty five"), Map.entry(26, "twenty six"), Map.entry(27, "twenty seven"), Map.entry(28, "twenty eight"),
            Map.entry(29, "twenty nine"), Map.entry(30, "half"), Map.entry(31, "thirty one"), Map.entry(32, "thirty two"),
            Map.entry(33, "thirty three"), Map.entry(34, "thirty four"), Map.entry(36, "thirty six"), Map.entry(37, "thirty seven"),
            Map.entry(38, "thirty eight"), Map.entry(39, "thirty nine")
    );

    private BritishTimeVocabulary() {
    }

    /**
     * Retrieves the British spoken word for a given hour.
     *
     * @param hour the hour value
     * @return the British word equivalent for the hour
     * @throws IllegalArgumentException if the hour is invalid or not supported
     */
    public static String getHourWord(int hour) {
        String hourWord = HOUR_WORDS.get(hour);
        if (hourWord == null) {
            throw new IllegalArgumentException("Invalid hour: " + hour);
        }
        return hourWord;
    }

    /**
     * Retrieves the spoken word for a minute.
     *
     * @param minute the minute value
     * @return the British English word equivalent for the minute
     * @throws IllegalArgumentException if the minute is invalid or not supported
     */
    public static String getMinuteWord(int minute) {
        String minuteWord = MINUTE_WORDS.get(minute);
        if (minuteWord == null) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        return minuteWord;
    }

    /**
     * Returns the display hour word, handling special cases for noon and midnight.
     *
     * @param time the time object
     * @return the British English word equivalent for the hour including special cases
     */
    public static String getDisplayHourWord(Time time) {
        if (time.isNoon()) return NOON;
        if (time.isMidnight()) return MIDNIGHT;
        return getHourWord(time.hour());
    }

}
