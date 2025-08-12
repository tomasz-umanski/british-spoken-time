package pl.umanski.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("To Time Strategy")
class ToTimeStrategyTest {

    private TimeFormatStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new ToTimeStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 35, true",
            "15, 59, true",
            "6, 15, false",
            "23, 39, false"
    })
    @DisplayName("Should correctly identify 'to' times")
    void shouldCorrectlyIdentifyToTimes(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format twenty five to three AM")
    void shouldFormatTwentyFiveToThreeAM() {
        Time time = new Time(2, 35);
        assertEquals("twenty five to three AM", strategy.format(time));
    }

    @Test
    @DisplayName("Should format quarter to eleven PM")
    void shouldFormatQuarterToElevenPM() {
        Time time = new Time(22, 45);
        assertEquals("quarter to eleven PM", strategy.format(time));
    }

    @Test
    @DisplayName("Should format one to noon")
    void shouldFormatOneToNoon() {
        Time time = new Time(11, 59);
        assertEquals("one to noon", strategy.format(time));
    }

    @Test
    @DisplayName("Should format one to midnight")
    void shouldFormatOneToMidnight() {
        Time time = new Time(23, 59);
        assertEquals("one to midnight", strategy.format(time));
    }

    @Test
    @DisplayName("Should format twenty to one PM after noon")
    void shouldFormatTwentyToOnePMAfterNoon() {
        Time time = new Time(12, 40);
        assertEquals("twenty to one PM", strategy.format(time));
    }

    @Test
    @DisplayName("Should format twenty to one AM after midnight")
    void shouldFormatTwentyToOneAMAfterMidnight() {
        Time time = new Time(0, 40);
        assertEquals("twenty to one AM", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "7, 58, two to eight AM",
            "3, 47, thirteen to four AM",
            "21, 51, nine to ten PM",
            "22, 55, five to eleven PM",
    })
    @DisplayName("Should format 'to' times with correct suffix")
    void shouldFormatToTimesWithCorrectSuffix(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.format(time));
    }

}
