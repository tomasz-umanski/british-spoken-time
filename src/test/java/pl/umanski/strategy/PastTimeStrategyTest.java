package pl.umanski.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Past Time Strategy Test")
class PastTimeStrategyTest {

    private TimeFormatStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new PastTimeStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 1, true",
            "16, 15, true",
            "2, 0, false",
            "21, 31, false",
    })
    @DisplayName("Should correctly identify 'past' times")
    void shouldCorrectlyIdentifyPastTimes(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format quarter past four AM")
    void shouldFormatQuarterPastFourAM() {
        Time time = new Time(4, 15);
        assertEquals("quarter past four AM", strategy.format(time));
    }

    @Test
    @DisplayName("Should format half past four PM")
    void shouldFormatHalfPastFourPM() {
        Time time = new Time(16, 30);
        assertEquals("half past four PM", strategy.format(time));
    }

    @Test
    @DisplayName("Should format one past midnight")
    void shouldFormatOnePastMidnightTime() {
        Time time = new Time(0, 1);
        assertEquals("one past midnight", strategy.format(time));
    }

    @Test
    @DisplayName("Should format eleven past noon")
    void shouldFormatElevenPastNoonTime() {
        Time time = new Time(12, 11);
        assertEquals("eleven past noon", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, five past five AM",
            "10, 10, ten past ten AM",
            "16, 18, eighteen past four PM",
            "23, 29, twenty nine past eleven PM",
    })
    @DisplayName("Should format 'past' times with correct suffix")
    void shouldFormatPastTimesWithCorrectSuffix(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.format(time));
    }

}
