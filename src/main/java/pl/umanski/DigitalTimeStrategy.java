package pl.umanski;

import static pl.umanski.BritishTimeVocabulary.*;

/**
 * Formatter for digital times (minutes 31-39 exclude 35).
 * Uses "digital" format like "one thirty six".
 */
public class DigitalTimeStrategy implements TimeFormatStrategy {

    @Override
    public boolean canHandle(Time time) {
        return time.minute() >= 31 && time.minute() <= 39 && time.minute() != 35;
    }

    @Override
    public String format(Time time) {
        String minuteWord = getMinuteWord(time.minute());
        String hourWord = getDisplayHourWord(time);
        return hourWord + " " + minuteWord;
    }

}
