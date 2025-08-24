package pl.umanski.converter;

import pl.umanski.model.Time;
import pl.umanski.strategy.TimeFormatStrategy;

import java.util.List;
import java.util.Objects;

/**
 * A strategy-based implementation of {@link SpokenTimeConverter} that uses
 * different formatting strategies and vocabulary to convert times.
 */
public class StrategyBasedTimeConverter implements SpokenTimeConverter {

    private final List<TimeFormatStrategy> strategies;

    /**
     * Creates a new converter with the given strategies and vocabulary.
     *
     * @param strategies the list of formatting strategies to use (order matters)
     * @throws IllegalArgumentException if strategies is null
     */
    public StrategyBasedTimeConverter(List<TimeFormatStrategy> strategies) {
        validateInput(strategies);
        this.strategies = strategies;
    }

    @Override
    public String convert(Time time) {
        if (Objects.isNull(time)) {
            throw new IllegalArgumentException("Time cannot be null");
        }

        return strategies.stream()
                .filter(strategy -> strategy.canHandle(time))
                .findFirst()
                .map(strategy -> strategy.format(time))
                .orElseThrow(() -> new IllegalStateException("No formatter found for time: " + time));
    }

    /**
     * Method that validates the provided strategies.
     *
     * @throws IllegalArgumentException if the input is null, empty, malformed
     */
    private static void validateInput(List<TimeFormatStrategy> strategies) {
        if (Objects.isNull(strategies)) {
            throw new IllegalArgumentException("strategies must not be null");
        }
        if (strategies.isEmpty()) {
            throw new IllegalArgumentException("strategies must not be empty");
        }
    }

}
