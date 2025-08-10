package pl.umanski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("To Time Strategy")
class ToTimeStrategyTest {

    private ToTimeStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new ToTimeStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 35, true",
            "5, 40, true",
            "5, 48, true",
            "5, 59, true",
            "5, 15, false",
            "5, 30, false",
            "5, 31, false",
            "5, 39, false"
    })
    @DisplayName("Should correctly identify to times")
    void shouldCorrectlyIdentifyToTimes(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format twenty five to three")
    void shouldFormatTwentyFiveToEight() {
        Time time = new Time(2, 35);
        assertEquals("twenty five to three", strategy.format(time));
    }

    @Test
    @DisplayName("Should format quarter to eleven")
    void shouldFormatQuarterToFour() {
        Time time = new Time(10, 45);
        assertEquals("quarter to eleven", strategy.format(time));
    }

    @Test
    @DisplayName("Should convert one to twelve")
    void shouldFormatOneToTwelve() {
        Time time = new Time(11, 59);
        assertEquals("one to twelve", strategy.format(time));
    }

    @Test
    @DisplayName("Should format twenty to one after noon")
    void shouldFormatTwentyToOneAfterNoon() {
        Time time = new Time(12, 40);
        assertEquals("twenty to one", strategy.format(time));
    }

    @Test
    @DisplayName("Should format twenty to one after midnight")
    void shouldFormatTwentyToOneAfterMidnight() {
        Time time = new Time(0, 40);
        assertEquals("twenty to one", strategy.format(time));
    }

    @Test
    @DisplayName("Should handle hour wrapping correctly")
    void shouldHandleHourWrappingCorrectly() {
        Time time = new Time(11, 59);
        assertEquals("one to twelve", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 41, nineteen to six",
            "7, 58, two to eight",
            "3, 47, thirteen to four",
            "1, 44, sixteen to two",
            "2, 51, nine to three",
            "10, 55, five to eleven",
    })
    @DisplayName("Should format 'to' times correctly")
    void shouldFormatToTimesCorrectly(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.format(time));
    }

}
