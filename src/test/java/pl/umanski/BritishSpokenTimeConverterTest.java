package pl.umanski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("British spoken time converter Test")
class BritishSpokenTimeConverterTest {

    private BritishSpokenTimeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new BritishSpokenTimeConverter();
    }

    @Test
    @DisplayName("Should throw exception when time is null")
    void shouldThrowExceptionWhenTimeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> converter.convert(null));
    }

    @Nested
    @DisplayName("Exact hour conversion")
    class ExactHourConversion {

        @Test
        @DisplayName("Should convert noon")
        void shouldConvertNoon() {
            Time time = new Time(12, 0);
            assertEquals("noon", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert midnight")
        void shouldConvertMidnight() {
            Time time = new Time(0, 0);
            assertEquals("midnight", converter.convert(time));
        }

        @ParameterizedTest
        @CsvSource({
                "1, one o'clock",
                "2, two o'clock",
                "3, three o'clock",
                "4, four o'clock",
                "5, five o'clock",
                "6, six o'clock",
                "7, seven o'clock",
                "8, eight o'clock",
                "9, nine o'clock",
                "10, ten o'clock",
                "11, eleven o'clock"
        })
        @DisplayName("Should convert regular hours")
        void shouldConvertRegularHours(int hour, String expected) {
            Time time = new Time(hour, 0);
            assertEquals(expected, converter.convert(time));
        }

    }

    @Nested
    @DisplayName("Past hour conversion")
    class PastHourConversion {

        @Test
        @DisplayName("Should convert quarter past four")
        void shouldConvertQuarterPastFour() {
            Time time = new Time(4, 15);
            assertEquals("quarter past four", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert half past seven")
        void shouldConvertHalfPastSeven() {
            Time time = new Time(7, 30);
            assertEquals("half past seven", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert one past midnight")
        void shouldConvertOnePastMidnight() {
            Time time = new Time(0, 1);
            assertEquals("one past midnight", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert eleven past noon")
        void shouldConvertElevenPastNoon() {
            Time time = new Time(12, 11);
            assertEquals("eleven past noon", converter.convert(time));
        }

        @ParameterizedTest
        @CsvSource({
                "1, 3, three past one",
                "2, 5, five past two",
                "3, 10, ten past three",
                "5, 20, twenty past five",
                "6, 25, twenty five past six",
                "7, 29, twenty nine past seven",
        })
        @DisplayName("Should convert time to past format")
        void shouldConvertTimeToPastFormat(int hour, int minute, String expected) {
            Time time = new Time(hour, minute);
            assertEquals(expected, converter.convert(time));
        }

    }

    @Nested
    @DisplayName("Digital format conversion")
    class DigitalFormatConversion {

        @ParameterizedTest
        @CsvSource({
                "1, 31, one thirty one",
                "2, 32, two thirty two",
                "3, 33, three thirty three",
                "4, 34, four thirty four",
                "5, 36, five thirty six",
                "6, 37, six thirty seven",
                "7, 38, seven thirty eight",
                "8, 39, eight thirty nine",
        })
        @DisplayName("Should convert time to digital format")
        void shouldConvertTimeToDigitalFormat(int hour, int minute, String expected) {
            Time time = new Time(hour, minute);
            assertEquals(expected, converter.convert(time));
        }

    }

}
