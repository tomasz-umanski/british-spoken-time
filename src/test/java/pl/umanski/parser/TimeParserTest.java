package pl.umanski.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Time Parser Test")
class TimeParserTest {

    @Test
    @DisplayName("Should parse valid time string")
    void shouldParseValidTimeString() {
        Time time = TimeParser.parse("10:30");
        assertEquals(new Time(10, 30), time);
    }

    @Test
    @DisplayName("Should parse time with leading zero")
    void shouldParseTimeWithLeadingZero() {
        Time time = TimeParser.parse("01:05");
        assertEquals(new Time(1, 5), time);
    }

    @Test
    @DisplayName("Should parse time without leading zero")
    void shouldParseTimeWithoutLeadingZero() {
        Time time = TimeParser.parse("4:28");
        assertEquals(new Time(4, 28), time);
    }

    @Test
    @DisplayName("Should parse midnight")
    void shouldParseMidnight() {
        Time time = TimeParser.parse("00:00");
        assertEquals(new Time(0, 0), time);
    }

    @Test
    @DisplayName("Should parse input with whitespaces")
    void shouldParseInputWithWhitespaces() {
        Time time = TimeParser.parse(" 5:28  ");
        assertEquals(new Time(5, 28), time);
    }

    @Test
    @DisplayName("Should parse noon")
    void shouldParseNoon() {
        Time time = TimeParser.parse("12:00");
        assertEquals(new Time(12, 0), time);
    }

    @Test
    @DisplayName("Should parse time in 24-hour format")
    void shouldParseTimeIn24HourFormat() {
        Time time = TimeParser.parse("19:30");
        assertEquals(new Time(19, 30), time);
    }

    @Test
    @DisplayName("Should throw exception for null input")
    void shouldThrowExceptionForNullInput() {
        assertThrows(IllegalArgumentException.class, () -> TimeParser.parse(null));
    }

    @Test
    @DisplayName("Should throw exception for empty input")
    void shouldThrowExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> TimeParser.parse(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"25:70", "10", "ab:cd", "test", "aa:30"})
    @DisplayName("Should throw exception for invalid input format")
    void shouldThrowExceptionForInvalidFormat(String input) {
        assertThrows(IllegalArgumentException.class, () -> TimeParser.parse(input));
    }

}
