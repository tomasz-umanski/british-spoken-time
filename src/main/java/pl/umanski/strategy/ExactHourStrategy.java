package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

/**
 * Formatter for exact hours (minute = 0).
 * Handles noon, midnight, and regular hours with "o'clock".
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
        return getHourWord(time.hour()) + " " + EXACT_HOUR_SUFFIX;
    }

}
