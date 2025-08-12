package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

/**
 * Formatter for exact hours (minute = 0).
 * Handles noon, midnight, and regular hours with "o'clock".
 * Adds AM/PM period suffix for regular hours.
 */
public class ExactHourStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.isExactHour();
    }

    @Override
    public String format(Time time) {
        if (time.isNoon()) return NOON;
        if (time.isMidnight()) return MIDNIGHT;

        String hourWord = getTwelveHourFormatWord(time.hour());
        String period = getPeriod(time.hour());

        return hourWord + " " + EXACT_HOUR_SUFFIX + " " + period;
    }

}
