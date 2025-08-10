package pl.umanski;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Exact Hour Strategy Test")
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

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

}
