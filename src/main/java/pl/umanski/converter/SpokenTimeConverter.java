package pl.umanski.converter;

import pl.umanski.model.Time;

/**
 * Interface for converting {@link Time} objects into their spoken representation
 */
public interface SpokenTimeConverter {

    /**
     * Converts a given {@link Time} instance to its spoken form.
     *
     * @param time the {@link Time} to convert
     * @return the spoken time representation
     * @throws IllegalArgumentException if time is null
     */
    String convert(Time time);

}
