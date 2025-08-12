package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

/**
 * Formatter for past times (minutes 1-30).
 * Uses "past" format like "quarter past four" or "twenty past five".
 * Adds AM/PM period suffix for regular hours.
 */
public class PastTimeStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.minute() >= 1 && time.minute() <= 30;
    }

    @Override
    public String format(Time time) {
        String minuteWord = getMinuteWord(time.minute());

        if (time.isNoon()) return minuteWord + " " + PAST_PREPOSITION + " " + NOON;
        if (time.isMidnight()) return minuteWord + " " + PAST_PREPOSITION + " " + MIDNIGHT;

        String hourWord = getTwelveHourFormatWord(time.hour());
        String period = getPeriod(time.hour());

        return minuteWord + " " + PAST_PREPOSITION + " " + hourWord + " " + period;
    }

}
