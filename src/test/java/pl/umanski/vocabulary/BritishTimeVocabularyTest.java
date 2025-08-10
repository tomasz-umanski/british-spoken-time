package pl.umanski.vocabulary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

@DisplayName("British Time Vocabulary Test")
class BritishTimeVocabularyTest {

    @ParameterizedTest
    @CsvSource({
            "0, twelve",
            "1, one",
            "2, two",
            "11, eleven"
    })
    @DisplayName("Should return correct hour words")
    void shouldReturnCorrectHourWords(int hour, String expected) {
        assertEquals(expected, getHourWord(hour));
    }

    @ParameterizedTest
    @CsvSource({
            "1, one",
            "15, quarter",
            "30, half",
            "25, twenty five"
    })
    @DisplayName("Should return correct minute words")
    void shouldReturnCorrectMinuteWords(int minute, String expected) {
        assertEquals(expected, getMinuteWord(minute));
    }

    @Test
    @DisplayName("Should return correct special words")
    void shouldReturnCorrectSpecialWords() {
        assertEquals("noon", NOON);
        assertEquals("midnight", MIDNIGHT);
        assertEquals("o'clock", EXACT_HOUR_SUFFIX);
        assertEquals("past", PAST_PREPOSITION);
        assertEquals("to", TO_PREPOSITION);
    }

    @Test
    @DisplayName("Should throw exception for not defined hour")
    void shouldThrowExceptionForNotDefinedHour() {
        assertThrows(IllegalArgumentException.class, () -> getHourWord(23));
    }

    @Test
    @DisplayName("Should throw exception for not defined minute")
    void shouldThrowExceptionForNotDefinedMinute() {
        assertThrows(IllegalArgumentException.class, () -> getMinuteWord(65));
    }

    @Test
    @DisplayName("Should return noon for 12:00")
    void shouldReturnNoonForTwelve() {
        Time time = new Time(12, 0);
        assertEquals("noon", getDisplayHourWord(time));
    }

    @Test
    @DisplayName("Should return noon at different minutes")
    void shouldReturnNoonAtDifferentMinutes() {
        Time time = new Time(12, 30);
        assertEquals("noon", getDisplayHourWord(time));
    }

    @Test
    @DisplayName("Should return midnight for 00:00")
    void shouldReturnMidnightForZero() {
        Time time = new Time(0, 0);
        assertEquals("midnight", getDisplayHourWord(time));
    }

    @Test
    @DisplayName("Should return midnight at different minutes")
    void shouldReturnMidnightAtDifferentMinutes() {
        Time time = new Time(0, 30);
        assertEquals("midnight", getDisplayHourWord(time));
    }

    @ParameterizedTest
    @CsvSource({
            "10, ten",
            "7, seven",
            "1, one",
            "3, three"
    })
    @DisplayName("Should return regular hour word for non-special time")
    void shouldReturnRegularHourWordForNonSpecialTimes(int hour, String expected) {
        Time time = new Time(hour, 0);
        assertEquals(expected, getDisplayHourWord(time));
    }

}
