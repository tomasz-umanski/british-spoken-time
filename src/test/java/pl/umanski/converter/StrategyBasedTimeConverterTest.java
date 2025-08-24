package pl.umanski.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.umanski.model.Time;
import pl.umanski.strategy.TimeFormatStrategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Strategy Based Time Converter Test")
class StrategyBasedTimeConverterTest {

    @Mock
    private TimeFormatStrategy strategy1;

    @Mock
    private TimeFormatStrategy strategy2;

    @Mock
    private TimeFormatStrategy strategy3;

    @Mock
    private Time time;

    private SpokenTimeConverter converter;

    @Nested
    @DisplayName("Constructor validation")
    class ConstructorValidation {

        @Test
        @DisplayName("Should create converter with valid strategies")
        void shouldCreateConverterWithValidStrategies() {
            List<TimeFormatStrategy> strategies = Arrays.asList(strategy1, strategy2);

            assertDoesNotThrow(() -> new StrategyBasedTimeConverter(strategies));
        }

        @Test
        @DisplayName("Should throw exception when strategies is null")
        void shouldThrowExceptionWhenStrategiesIsNull() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new StrategyBasedTimeConverter(null)
            );
            assertEquals("strategies must not be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw exception when strategies is empty")
        void shouldThrowExceptionWhenStrategiesIsEmpty() {
            List<TimeFormatStrategy> emptyStrategies = Collections.emptyList();

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new StrategyBasedTimeConverter(emptyStrategies)
            );
            assertEquals("strategies must not be empty", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Time conversion")
    class TimeConversion {

        @BeforeEach
        void setUp() {
            converter = new StrategyBasedTimeConverter(Arrays.asList(strategy1, strategy2, strategy3));
        }

        @Test
        @DisplayName("Should throw exception when time is null")
        void shouldThrowExceptionWhenTimeIsNull() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> converter.convert(null)
            );
            assertEquals("Time cannot be null", exception.getMessage());
        }

        @Test
        @DisplayName("Should use first matching strategy")
        void shouldUseFirstMatchingStrategy() {
            when(strategy1.canHandle(time)).thenReturn(true);
            when(strategy1.format(time)).thenReturn("first strategy result");

            String result = converter.convert(time);

            assertEquals("first strategy result", result);
            verify(strategy1).canHandle(time);
            verify(strategy1).format(time);
            verify(strategy2, never()).canHandle(time);
            verify(strategy3, never()).canHandle(time);
        }

        @Test
        @DisplayName("Should use second strategy when first cannot handle")
        void shouldUseSecondStrategyWhenFirstCannotHandle() {
            when(strategy1.canHandle(time)).thenReturn(false);
            when(strategy2.canHandle(time)).thenReturn(true);
            when(strategy2.format(time)).thenReturn("second strategy result");

            String result = converter.convert(time);

            assertEquals("second strategy result", result);
            verify(strategy1).canHandle(time);
            verify(strategy2).canHandle(time);
            verify(strategy2).format(time);
            verify(strategy3, never()).canHandle(time);
            verify(strategy1, never()).format(time);
        }

        @Test
        @DisplayName("Should use third strategy when first two cannot handle")
        void shouldUseThirdStrategyWhenFirstTwoCannotHandle() {
            when(strategy1.canHandle(time)).thenReturn(false);
            when(strategy2.canHandle(time)).thenReturn(false);
            when(strategy3.canHandle(time)).thenReturn(true);
            when(strategy3.format(time)).thenReturn("third strategy result");

            String result = converter.convert(time);

            assertEquals("third strategy result", result);
            verify(strategy1).canHandle(time);
            verify(strategy2).canHandle(time);
            verify(strategy3).canHandle(time);
            verify(strategy3).format(time);
            verify(strategy1, never()).format(time);
            verify(strategy2, never()).format(time);
        }

        @Test
        @DisplayName("Should throw exception when no strategy can handle time")
        void shouldThrowExceptionWhenNoStrategyCanHandleTime() {
            when(strategy1.canHandle(time)).thenReturn(false);
            when(strategy2.canHandle(time)).thenReturn(false);
            when(strategy3.canHandle(time)).thenReturn(false);
            when(time.toString()).thenReturn("10:30");

            IllegalStateException exception = assertThrows(
                    IllegalStateException.class,
                    () -> converter.convert(time)
            );
            assertEquals("No formatter found for time: 10:30", exception.getMessage());

            verify(strategy1).canHandle(time);
            verify(strategy2).canHandle(time);
            verify(strategy3).canHandle(time);
            verify(strategy1, never()).format(time);
            verify(strategy2, never()).format(time);
            verify(strategy3, never()).format(time);
        }

        @Test
        @DisplayName("Should call strategies multiple times for multiple conversions")
        void shouldCallStrategiesMultipleTimesForMultipleConversions() {
            when(strategy1.canHandle(time)).thenReturn(true);
            when(strategy1.format(time)).thenReturn("result");

            converter.convert(time);
            converter.convert(time);

            verify(strategy1, times(2)).canHandle(time);
            verify(strategy1, times(2)).format(time);
        }

    }

}