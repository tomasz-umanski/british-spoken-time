package pl.umanski.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Digital Time Strategy")
class DigitalTimeStrategyTest {

    private TimeFormatStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DigitalTimeStrategy();
    }

    @ParameterizedTest
    @CsvSource({
            "5, 31, true",
            "5, 32, true",
            "5, 39, true",
            "5, 15, false",
            "5, 35, false",
            "5, 30, false",
            "5, 40, false"
    })
    @DisplayName("Should correctly identify digital times")
    void shouldCorrectlyIdentifyDigitalTimes(int hour, int minute, boolean expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.canHandle(time));
    }

    @Test
    @DisplayName("Should format digital noon time")
    void shouldFormatDigitalNoonCorrectly() {
        Time time = new Time(12, 39);
        assertEquals("noon thirty nine", strategy.format(time));
    }

    @Test
    @DisplayName("Should format digital midnight time")
    void shouldFormatDigitalMidnightCorrectly() {
        Time time = new Time(0, 34);
        assertEquals("midnight thirty four", strategy.format(time));
    }

    @ParameterizedTest
    @CsvSource({
            "3, 31, three thirty one",
            "7, 32, seven thirty two",
            "3, 33, three thirty three",
            "1, 36, one thirty six",
            "2, 37, two thirty seven",
            "11, 38, eleven thirty eight",
    })
    @DisplayName("Should format digital times correctly")
    void shouldFormatDigitalTimesCorrectly(int hour, int minute, String expected) {
        Time time = new Time(hour, minute);
        assertEquals(expected, strategy.format(time));
    }

}
