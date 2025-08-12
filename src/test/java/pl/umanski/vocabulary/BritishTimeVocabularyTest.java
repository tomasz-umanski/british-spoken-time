package pl.umanski.vocabulary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.umanski.vocabulary.BritishTimeVocabulary.*;

@DisplayName("British Time Vocabulary Test")
class BritishTimeVocabularyTest {

    @ParameterizedTest
    @CsvSource({
            "2, two",
            "6, six",
            "14, two",
            "23, eleven"
    })
    @DisplayName("Should return correct hour words in 12-hour format")
    void shouldReturnCorrectHourWordsIn12HourFormat(int hour, String expected) {
        assertEquals(expected, getTwelveHourFormatWord(hour));
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
        assertEquals("AM", AM);
        assertEquals("PM", PM);
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 12, 24})
    @DisplayName("Should throw exception for not defined hours")
    void shouldThrowExceptionForNotDefinedHours(int hour) {
        assertThrows(IllegalArgumentException.class, () -> getTwelveHourFormatWord(hour));
    }

    @Test
    @DisplayName("Should throw exception for not defined minute")
    void shouldThrowExceptionForNotDefinedMinute() {
        assertThrows(IllegalArgumentException.class, () -> getMinuteWord(65));
    }

    @ParameterizedTest
    @CsvSource({
            "0, AM",
            "1, AM",
            "7, AM",
            "12, PM",
            "14, PM",
            "23, PM"
    })
    @DisplayName("Should determine period based on hour")
    void shouldDeterminePeriodBasedOnHour(int hour, String expected) {
        assertEquals(expected, getPeriod(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 100})
    @DisplayName("Should throw exception for hour out of period range")
    void shouldThrowExceptionForHourOutOfPeriodRange(int hour) {
        assertThrows(IllegalArgumentException.class, () -> getPeriod(hour));
    }

}
