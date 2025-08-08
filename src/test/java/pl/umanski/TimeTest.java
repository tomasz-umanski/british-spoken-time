package pl.umanski;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Time record test")
class TimeTest {

    @Nested
    @DisplayName("Valid time creation")
    class ValidTimeCreation {

        @Test
        @DisplayName("Should create time for valid hour and minute")
        void shouldCreateTimeForValidHourAndMinute() {
            Time time = new Time(10, 30);

            assertEquals(10, time.hour());
            assertEquals(30, time.minute());
        }

        @Test
        @DisplayName("Should create time for midnight (00:00)")
        void shouldCreateTimeForMidnight() {
            Time time = new Time(0, 0);
            assertEquals(0, time.hour());
            assertEquals(0, time.minute());
        }

        @Test
        @DisplayName("Should create time for noon (12:00)")
        void shouldCreateTimeForNoon() {
            Time time = new Time(12, 0);
            assertEquals(12, time.hour());
            assertEquals(0, time.minute());
        }

        @Test
        @DisplayName("Should create time for last possible hour and minute (12:59)")
        void shouldCreateTimeForLastPossibleHourAndMinute() {
            Time time = new Time(12, 59);
            assertEquals(12, time.hour());
            assertEquals(59, time.minute());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 6, 11})
        @DisplayName("Should create time for valid hours")
        void shouldCreateTimeForValidHours(int hour) {
            Time time = new Time(hour, 30);
            assertEquals(hour, time.hour());
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 15, 25, 45})
        @DisplayName("Should create time for valid minutes")
        void shouldCreateTimeForValidMinutes(int minute) {
            Time time = new Time(10, minute);

            assertEquals(10, time.hour());
            assertEquals(minute, time.minute());
        }

    }

    @Nested
    @DisplayName("Invalid time creation")
    class InvalidTimeCreation {

        @Test
        @DisplayName("Should throw exception for hour -1")
        void shouldThrowExceptionForHourBelowMinimum() {
            assertThrows(IllegalArgumentException.class, () -> new Time(-1, 30));
        }

        @Test
        @DisplayName("Should throw exception for hour 13")
        void shouldThrowExceptionForHourAboveMaximum() {
            assertThrows(IllegalArgumentException.class, () -> new Time(13, 30));
        }

        @Test
        @DisplayName("Should throw exception for minute -1")
        void shouldThrowExceptionForMinuteBelowMinimum() {
            assertThrows(IllegalArgumentException.class, () -> new Time(10, -1));
        }

        @Test
        @DisplayName("Should throw exception for minute 60")
        void shouldThrowExceptionForMinuteAboveMaximum() {
            assertThrows(IllegalArgumentException.class, () -> new Time(10, 60));
        }

        @ParameterizedTest
        @ValueSource(ints = {-20, 24, 100})
        @DisplayName("Should throw exception for invalid hours")
        void shouldThrowExceptionForInvalidHours(int invalidHour) {
            assertThrows(IllegalArgumentException.class, () -> new Time(invalidHour, 30));
        }

        @ParameterizedTest
        @ValueSource(ints = {-20, 61, 120})
        @DisplayName("Should throw exception for invalid minutes")
        void shouldThrowExceptionForInvalidMinutes(int invalidMinute) {
            assertThrows(IllegalArgumentException.class, () -> new Time(10, invalidMinute));
        }

    }

    @Nested
    @DisplayName("Time equality")
    class TimeEquality {

        @Test
        @DisplayName("Should be equal when hours and minutes are same")
        void shouldBeEqualWhenHoursAndMinutesAreSame() {
            Time time1 = new Time(10, 30);
            Time time2 = new Time(10, 30);

            assertEquals(time1, time2);
            assertEquals(time1.hashCode(), time2.hashCode());
        }

        @Test
        @DisplayName("Should not be equal when hours differs")
        void shouldNotBeEqualWhenHourDiffers() {
            Time time1 = new Time(10, 30);
            Time time2 = new Time(11, 30);

            assertNotEquals(time1, time2);
        }

        @Test
        @DisplayName("Should not be equal when minutes differs")
        void shouldNotBeEqualWhenMinuteDiffers() {
            Time time1 = new Time(10, 30);
            Time time2 = new Time(10, 31);

            assertNotEquals(time1, time2);
        }

        @Test
        @DisplayName("Should not be equal to null")
        void shouldNotBeEqualToNull() {
            Time time = new Time(10, 30);
            assertNotEquals(null, time);
        }

    }

}