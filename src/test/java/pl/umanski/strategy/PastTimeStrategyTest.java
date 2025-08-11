package pl.umanski.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Past Time Strategy")
class PastTimeStrategyTest {

    private TimeFormatStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new PastTimeStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 1, true",
            "5, 15, true",
            "5, 30, true",
            "5, 0, false",
            "5, 31, false",
            "5, 45, false"
    })
    @DisplayName("Should correctly identify past times")
    void shouldCorrectlyIdentifyPastTimes(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format quarter past time")
    void shouldFormatQuarterPastTime() {
        Time time = new Time(4, 15);
        assertEquals("quarter past four", strategy.format(time));
    }

    @Test
    @DisplayName("Should format half past time")
    void shouldFormatHalfPastTime() {
        Time time = new Time(4, 30);
        assertEquals("half past four", strategy.format(time));
    }

    @Test
    @DisplayName("Should format past midnight time")
    void shouldFormatPastMidnightTime() {
        Time time = new Time(0, 1);
        assertEquals("one past midnight", strategy.format(time));
    }

    @Test
    @DisplayName("Should format past noon time")
    void shouldFormatPastNoonTime() {
        Time time = new Time(12, 11);
        assertEquals("eleven past noon", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, five past five",
            "10, 10, ten past ten",
            "11, 29, twenty nine past eleven",
    })
    @DisplayName("Should format time to past")
    void shouldFormatTimeToPast(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.format(time));
    }

}
