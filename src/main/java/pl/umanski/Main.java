package pl.umanski;

import pl.umanski.converter.BritishSpokenTimeConverter;
import pl.umanski.converter.SpokenTimeConverter;
import pl.umanski.model.Time;
import pl.umanski.parser.TimeParser;

import java.util.Scanner;

/**
 * Main class for the British Spoken Time Converter application.
 * Supports both interactive CLI mode and command line argument processing.
 */
public class Main {

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments containing time strings to convert.
     *             If no arguments provided, starts interactive mode.
     */
    public static void main(String[] args) {
        SpokenTimeConverter converter = new BritishSpokenTimeConverter();

        if (args.length > 0) {
            processCommandLineArguments(args, converter);
        } else {
            runInteractiveMode(converter);
        }
    }

    /**
     * Processes time strings provided as command line arguments.
     * Each argument is processed independently and results are printed immediately.
     *
     * @param args      Array of time strings to process
     * @param converter The time converter to use
     */
    private static void processCommandLineArguments(String[] args, SpokenTimeConverter converter) {
        for (String timeInput : args) {
            processTime(timeInput.trim(), converter);
        }
    }

    /**
     * Runs the interactive CLI mode where users can input times interactively.
     * Continues until user enters 'quit' or input stream becomes unavailable.
     *
     * @param converter The time converter to use
     */
    private static void runInteractiveMode(SpokenTimeConverter converter) {
        try (Scanner scanner = new Scanner(System.in)) {
            printWelcomeMessage();

            while (true) {
                System.out.print("> ");

                if (!scanner.hasNextLine()) {
                    System.out.println("No input available, exiting...");
                    break;
                }

                String input = scanner.nextLine().trim();

                if (isQuitCommand(input)) {
                    System.out.println("Goodbye!");
                    break;
                }

                processTime(input, converter);
            }
        } catch (Exception e) {
            System.out.println("Unexpected error occurred, exiting...");
        }
    }

    /**
     * Prints the welcome message and usage instructions.
     */
    private static void printWelcomeMessage() {
        System.out.println("British Spoken Time Converter");
        System.out.println("Enter time in HH:MM or H:MM format (or 'quit' to exit):");
    }

    /**
     * Checks if the input is a quit command (case-insensitive).
     *
     * @param input The user input to check
     * @return true if input is a quit command, false otherwise
     */
    private static boolean isQuitCommand(String input) {
        return "quit".equalsIgnoreCase(input);
    }

    /**
     * Processes a single time string and outputs the result or error message.
     *
     * @param input     The time string to process
     * @param converter The time converter to use
     */
    private static void processTime(String input, SpokenTimeConverter converter) {
        try {
            validateInput(input);

            Time parsedTime = TimeParser.parse(input);
            String spokenTime = converter.convert(parsedTime);
            System.out.println("Spoken time: " + spokenTime);

        } catch (IllegalArgumentException e) {
            printErrorMessage(e.getMessage());
        }
    }

    /**
     * Validates that input is not null or empty.
     *
     * @param input The input to validate
     * @throws IllegalArgumentException if input is null or empty
     */
    private static void validateInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty");
        }
    }

    /**
     * Prints a formatted error message with usage instructions.
     *
     * @param errorMessage The specific error message to display
     */
    private static void printErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
        System.out.println("Please enter time in HH:MM or H:MM format (e.g., 7:30)");
    }

}