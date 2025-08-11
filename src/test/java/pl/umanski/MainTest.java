package pl.umanski;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Main Application Test")
@Timeout(value = 10, unit = TimeUnit.SECONDS)
class MainTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Nested
    @DisplayName("Interactive CLI Mode Tests")
    class InteractiveModeTests {

        @Test
        @DisplayName("Should print required instructions and quit")
        void shouldPrintRequiredInstructionsAndQuit() {
            String input = "quit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should convert valid time and quit")
        void shouldConvertValidTimeAndQuit() {
            String input = "10:30\nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("Spoken time:"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should print error for invalid time input and quit")
        void shouldPrintErrorForInvalidTimeInputAndQuit() {
            String input = "test\nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should print error for invalid time input then convert valid time and quit")
        void shouldPrintErrorForInvalidTimeInputThenConvertValidTimeAndQuit() {
            String input = "test\n10:30\nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Spoken time:"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should convert valid time then print error for invalid time and quit")
        void shouldConvertValidTimeThenPrintErrorForInvalidTimeAndQuit() {
            String input = "10:30\ntest\nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("Spoken time:"));
            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should handle input with whitespaces from user input")
        void shouldHandleInputWithWhitespacesFromUserInput() {
            String input = " 10:30 \n test\nquit  \n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("Spoken time:"));
            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should handle empty input and quit")
        void shouldHandleEmptyInputAndQuit() {
            String input = "\nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should handle input with whitespace and quit")
        void shouldHandleInputWithWhitespaceAndQuit() {
            String input = "   \nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should handle multiple empty inputs and quit")
        void shouldHandleMultipleEmptyInputsAndQuit() {
            String input = "\n\n    \nquit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should handle empty command line arguments array")
        void shouldHandleEmptyCommandLineArgumentsArray() {
            String input = "quit\n";
            provideInput(input);

            Main.main(new String[]{});

            String output = getOutput();

            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should handle empty stream")
        void shouldHandleEmptyStream() {
            ByteArrayInputStream emptyStream = new ByteArrayInputStream(new byte[0]);
            System.setIn(emptyStream);

            assertDoesNotThrow(() -> Main.main(new String[]{}));

            String output = getOutput();

            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("No input available, exiting..."));
        }

        @Test
        @DisplayName("Should handle interrupted input stream")
        void shouldHandleInterruptedInputStream() {
            ByteArrayInputStream closedStream = new ByteArrayInputStream("".getBytes());
            closedStream.mark(0);
            try {
                closedStream.close();
            } catch (Exception ignore) {
            }
            System.setIn(closedStream);

            assertDoesNotThrow(() -> Main.main(new String[]{}));

            String output = getOutput();
            assertTrue(output.contains("British Spoken Time Converter"));
            assertTrue(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertTrue(output.contains("No input available, exiting..."));
        }
    }

    @Nested
    @DisplayName("Command Line Arguments Tests")
    class CommandLineArgumentsTests {

        @Test
        @DisplayName("Should convert valid time from command line arguments")
        void shouldConvertTimeFromCommandLineArguments() {
            Main.main(new String[]{"10:30"});

            String output = getOutput();

            assertTrue(output.contains("Spoken time:"));
        }

        @Test
        @DisplayName("Should handle invalid time from command line arguments")
        void shouldHandleInvalidTimeFromCommandLineArguments() {
            Main.main(new String[]{"invalid"});

            String output = getOutput();

            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("Please enter time in HH:MM or H:MM format (e.g., 7:30)"));
        }

        @Test
        @DisplayName("Should handle multiple command line arguments")
        void shouldHandleMultipleCommandLineArguments() {
            Main.main(new String[]{"10:30", "11:45", "invalid"});

            String output = getOutput();

            assertTrue(output.contains("Spoken time:"));
            assertTrue(output.contains("Error:"));
        }

        @Test
        @DisplayName("Should not provide interactive CLI for command line arguments")
        void shouldNotProvideInteractiveCLIForCommandLineArguments() {
            Main.main(new String[]{"10:30"});
            String output = getOutput();

            assertFalse(output.contains("Enter time in HH:MM or H:MM format (or 'quit' to exit):"));
            assertFalse(output.contains("Goodbye!"));
        }

        @Test
        @DisplayName("Should trim whitespace from command line argument")
        void shouldTrimWhitespaceFromCommandLineArgument() {
            Main.main(new String[]{"   10:30  "});

            String output = getOutput();

            assertTrue(output.contains("Spoken time:"));
        }

    }

}
