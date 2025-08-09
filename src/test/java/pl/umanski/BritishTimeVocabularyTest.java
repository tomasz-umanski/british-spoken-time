package pl.umanski;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.umanski.BritishTimeVocabulary.*;

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

}
