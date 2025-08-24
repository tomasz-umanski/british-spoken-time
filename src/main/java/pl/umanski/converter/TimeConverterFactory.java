package pl.umanski.converter;

import pl.umanski.strategy.british.DigitalTimeStrategy;
import pl.umanski.strategy.british.ExactHourStrategy;
import pl.umanski.strategy.british.PastTimeStrategy;
import pl.umanski.strategy.british.ToTimeStrategy;

import java.util.List;

/**
 * Factory for creating different types of spoken time converters.
 */
public class TimeConverterFactory {

    /**
     * Creates a British English spoken time converter.
     *
     * @return a configured converter for British English
     */
    public static SpokenTimeConverter createBritishEnglishConverter() {
        return new StrategyBasedTimeConverter(
                List.of(
                        new ExactHourStrategy(),
                        new PastTimeStrategy(),
                        new DigitalTimeStrategy(),
                        new ToTimeStrategy()
                )
        );
    }

}
