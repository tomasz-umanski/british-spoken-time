package pl.umanski;

import java.util.List;

/**
 * Converts {@link Time} objects into their British spoken representation.
 */
public class BritishSpokenTimeConverter implements SpokenTimeConverter {

    private final List<TimeFormatStrategy> strategies;

    /**
     * Creates a new converter with default strategies in order.
     */
    public BritishSpokenTimeConverter() {
        this.strategies = List.of(
                new ExactHourStrategy(),
                new PastTimeStrategy(),
                new DigitalTimeStrategy(),
                new ToTimeStrategy()
        );
    }

    /**
     * Converts a given {@link Time} instance to its British spoken form.
     *
     * @param time the {@link Time} to convert
     * @return the British spoken time representation
     * @throws IllegalArgumentException if time is null
     */
    public String convert(Time time) {
        if (time == null) {
            throw new IllegalArgumentException("time must not be null");
        }

        return strategies.stream()
                .filter(strategy -> strategy.canHandle(time))
                .findFirst()
                .map(strategy -> strategy.format(time))
                .orElseThrow(() -> new IllegalStateException("No formatter found for time: " + time));
    }

}
