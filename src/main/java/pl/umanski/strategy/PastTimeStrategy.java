package pl.umanski.strategy;

import pl.umanski.model.Time;

import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

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

}
