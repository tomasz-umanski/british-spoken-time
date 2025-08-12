package pl.umanski.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.umanski.model.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("British Spoken Time Converter Test")
class BritishSpokenTimeConverterTest {

    private SpokenTimeConverter converter;

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
                "4, four o'clock AM",
                "11, eleven o'clock AM",
                "19, seven o'clock PM",
                "23, eleven o'clock PM"
        })
        @DisplayName("Should convert regular hours with correct suffix")
        void shouldConvertRegularHoursWithCorrectSuffix(int hour, String expected) {
            Time time = new Time(hour, 0);
            assertEquals(expected, converter.convert(time));
        }

    }

    @Nested
    @DisplayName("Past hour conversion")
    class PastHourConversion {

        @Test
        @DisplayName("Should convert quarter past four AM")
        void shouldConvertQuarterPastFourAM() {
            Time time = new Time(4, 15);
            assertEquals("quarter past four AM", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert half past seven AM")
        void shouldConvertHalfPastSevenAM() {
            Time time = new Time(7, 30);
            assertEquals("half past seven AM", converter.convert(time));
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
                "2, 5, five past two AM",
                "7, 29, twenty nine past seven AM",
                "17, 8, eight past five PM",
                "21, 14, fourteen past nine PM",
        })
        @DisplayName("Should convert time to past format with correct suffix")
        void shouldConvertTimeToPastFormatWithCorrectSuffix(int hour, int minute, String expected) {
            Time time = new Time(hour, minute);
            assertEquals(expected, converter.convert(time));
        }

    }

    @Nested
    @DisplayName("Digital format conversion")
    class DigitalFormatConversion {

        @ParameterizedTest
        @CsvSource({
                "2, 32, two thirty two AM",
                "7, 38, seven thirty eight AM",
                "13, 36, one thirty six PM",
                "23, 31, eleven thirty one PM",
        })
        @DisplayName("Should convert time to digital format with correct suffix")
        void shouldConvertTimeToDigitalFormatWithCorrectSuffix(int hour, int minute, String expected) {
            Time time = new Time(hour, minute);
            assertEquals(expected, converter.convert(time));
        }

    }

    @Nested
    @DisplayName("To hour conversion")
    class ToHourConversion {

        @Test
        @DisplayName("Should convert twenty five to eight AM")
        void shouldConvertTwentyFiveToEightAM() {
            Time time = new Time(7, 35);
            assertEquals("twenty five to eight AM", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert quarter to four PM")
        void shouldConvertQuarterToFourPM() {
            Time time = new Time(15, 45);
            assertEquals("quarter to four PM", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert one to noon")
        void shouldConvertOneToNoon() {
            Time time = new Time(11, 59);
            assertEquals("one to noon", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert one to midnight")
        void shouldConvertOneToMidnight() {
            Time time = new Time(23, 59);
            assertEquals("one to midnight", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert twenty to one PM")
        void shouldConvertTwentyToOnePMAfterNoon() {
            Time time = new Time(12, 40);
            assertEquals("twenty to one PM", converter.convert(time));
        }

        @Test
        @DisplayName("Should convert twenty to one AM")
        void shouldConvertTwentyToOneAMAfterMidnight() {
            Time time = new Time(0, 40);
            assertEquals("twenty to one AM", converter.convert(time));
        }

        @ParameterizedTest
        @CsvSource({
                "3, 40, twenty to four AM",
                "8, 50, ten to nine AM",
                "19, 48, twelve to eight PM",
                "22, 55, five to eleven PM"
        })
        @DisplayName("Should convert time to 'to' format with correct suffix")
        void shouldConvertTimeToToFormatWithCorrectSuffix(int hour, int minute, String expected) {
            Time time = new Time(hour, minute);
            assertEquals(expected, converter.convert(time));
        }

    }

}
