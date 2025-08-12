package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

/**
 * Formatter for digital times (minutes 31-39 exclude 35).
 * Uses "digital" format like "one thirty six".
 * Adds AM/PM period suffix for regular hours.
 */
public class DigitalTimeStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.minute() >= 31 && time.minute() <= 39 && time.minute() != 35;
    }

    @Override
    public String format(Time time) {
        String minuteWord = getMinuteWord(time.minute());

        if (time.isNoon()) return NOON + " " + minuteWord;
        if (time.isMidnight()) return MIDNIGHT + " " + minuteWord;

        String hourWord = getTwelveHourFormatWord(time.hour());
        String period = getPeriod(time.hour());

        return hourWord + " " + minuteWord + " " + period;
    }

}
