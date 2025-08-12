package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

/**
 * Formatter for 'to' times (minutes 40-60 and 35).
 * Uses "to" format like "twenty five to five" or "five to ten".
 * Adds AM/PM period suffix for regular hours.
 */
public class ToTimeStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.minute() == 35 || time.minute() >= 40;
    }

    @Override
    public String format(Time time) {
        int nextHour = time.hour() + 1;
        int minutesTo = 60 - time.minute();
        String minuteWord = getMinuteWord(minutesTo);

        if (nextHour == 12) return minuteWord + " " + TO_PREPOSITION + " " + NOON;
        if (nextHour == 24) return minuteWord + " " + TO_PREPOSITION + " " + MIDNIGHT;

        String hourWord = getTwelveHourFormatWord(nextHour);
        String period = getPeriod(nextHour);

        return minuteWord + " " + TO_PREPOSITION + " " + hourWord + " " + period;
    }

}
