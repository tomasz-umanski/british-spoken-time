package pl.umanski;

/**
 * Strategy interface for formatting time in different ways.
 */
public interface TimeFormatStrategy {

    /**
     * Checks if this formatter can handle the given time.
     *
     * @param time the time to check
     * @return true if this formatter can handle the time, false otherwise
     */
    boolean canHandle(Time time);

    /**
     * Formats the time according to this formatter's rules.
     *
     * @param time the time to format
     * @return the formatted time string
     */
    String format(Time time);

}
