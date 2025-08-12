package pl.umanski.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Digital Time Strategy Test")
class DigitalTimeStrategyTest {

    private TimeFormatStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DigitalTimeStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 31, true",
            "15, 39, true",
            "1, 15, false",
            "21, 35, false",
            "23, 48, false"
    })
    @DisplayName("Should correctly identify 'digital' times")
    void shouldCorrectlyIdentifyDigitalTimes(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format noon thirty nine")
    void shouldFormatNoonThirtyNine() {
        Time time = new Time(12, 39);
        assertEquals("noon thirty nine", strategy.format(time));
    }

    @Test
    @DisplayName("Should format midnight thirty four")
    void shouldFormatMidnightThirtyFour() {
        Time time = new Time(0, 34);
        assertEquals("midnight thirty four", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "7, 32, seven thirty two AM",
            "3, 33, three thirty three AM",
            "16, 36, four thirty six PM",
            "22, 37, ten thirty seven PM",
    })
    @DisplayName("Should format 'digital' times with correct suffix")
    void shouldFormatDigitalTimesWithCorrectSuffix(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.format(time));
    }

}
