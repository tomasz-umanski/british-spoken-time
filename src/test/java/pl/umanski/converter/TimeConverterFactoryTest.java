package pl.umanski.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Time Converter Factory Test")
class TimeConverterFactoryTest {

    @Nested
    @DisplayName("British English converter creation")
    class BritishEnglishConverterCreation {

        @Test
        @DisplayName("Should create non-null converter")
        void shouldCreateNonNullConverter() {
            SpokenTimeConverter converter = TimeConverterFactory.createBritishEnglishConverter();

            assertNotNull(converter);
        }

        @Test
        @DisplayName("Should create StrategyBasedTimeConverter instance")
        void shouldCreateStrategyBasedTimeConverterInstance() {
            SpokenTimeConverter converter = TimeConverterFactory.createBritishEnglishConverter();

            assertInstanceOf(StrategyBasedTimeConverter.class, converter);
        }

        @Test
        @DisplayName("Should create new instance on each call")
        void shouldCreateNewInstanceOnEachCall() {
            SpokenTimeConverter converter1 = TimeConverterFactory.createBritishEnglishConverter();
            SpokenTimeConverter converter2 = TimeConverterFactory.createBritishEnglishConverter();

            assertNotSame(converter1, converter2);
        }

    }

}