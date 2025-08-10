package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

/**
 * Formatter for 'to' times (minutes 40-60 and 35).
 * Uses "to" format like "twenty five to five" or "five to ten".
 */
public class ToTimeStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.minute() == 35 || time.minute() >= 40;
    }

    @Override
    public String format(Time time) {
        int minutesTo = 60 - time.minute();
        int nextHour = (time.hour() + 1) % 12;

        String minuteWord = getMinuteWord(minutesTo);
        String nextHourWord = nextHour == 0 ? TWELVE : getHourWord(nextHour);

        return minuteWord + " " + TO_PREPOSITION + " " + nextHourWord;
    }

}
