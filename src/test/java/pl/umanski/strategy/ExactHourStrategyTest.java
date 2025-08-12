package pl.umanski.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exact Hour Strategy Test")
class ExactHourStrategyTest {

    private TimeFormatStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new ExactHourStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "16, 0, true",
            "4, 25, false",
            "23, 15, false",
    })
    @DisplayName("Should correctly identify 'exact' hours")
    void shouldCorrectlyIdentifyExactHours(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format noon correctly")
    void shouldFormatNoonCorrectly() {
        Time time = new Time(12, 0);
        assertEquals("noon", strategy.format(time));
    }

    @Test
    @DisplayName("Should format midnight correctly")
    void shouldFormatMidnightCorrectly() {
        Time time = new Time(0, 0);
        assertEquals("midnight", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "1, one o'clock AM",
            "3, three o'clock AM",
            "17, five o'clock PM",
            "20, eight o'clock PM",
    })
    @DisplayName("Should format regular 'exact' hours with correct suffix")
    void shouldFormatRegularExactHoursWithCorrectSuffix(int hour, String expected) {
        Time time = new Time(hour, 0);
        assertEquals(expected, strategy.format(time));
    }

}
