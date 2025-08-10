package pl.umanski;

import static pl.umanski.BritishTimeVocabulary.*;

/**
 * Formatter for past times (minutes 1-30).
 * Uses "past" format like "quarter past four" or "twenty past five".
 */
public class PastTimeStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.minute() >= 1 && time.minute() <= 30;
    }

    @Override
    public String format(Time time) {
        String minuteWord = getMinuteWord(time.minute());
        String hourWord = getDisplayHourWord(time);
        return minuteWord + " " + PAST_PREPOSITION + " " + hourWord;
    }

    /**
     * Returns the display hour word, handling special cases for noon and midnight.
     */
    private String getDisplayHourWord(Time time) {
        if (time.isNoon()) return NOON;
        if (time.isMidnight()) return MIDNIGHT;
        return getHourWord(time.hour());
    }

}
