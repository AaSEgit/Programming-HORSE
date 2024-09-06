package Tests;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.ProgrammingHorse;
public class programmingHorseTests {
    @Test
    void testMenuOption1() {
        // Define mock user input for menu selection
        String mockInput = "john\njames\n1"; // Simulate selecting option 1 (display rules)
        ByteArrayInputStream in = new ByteArrayInputStream(mockInput.getBytes());

        // Redirect System.in to use the mock input stream
        System.setIn(in);


        // Call the main method
        ProgrammingHorse.main(new String[]{});

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Verify that the output contains the expected messages
        String output = outContent.toString();
        assertTrue(output.contains("Rules"));
    }

    @Test
    void testMenuOption2() {
        // Define mock user input for menu selection
        String mockInput = "john\njames\n2\n"; // Simulate selecting option 2 (play game)
        ByteArrayInputStream in = new ByteArrayInputStream(mockInput.getBytes());

        // Redirect System.in to use the mock input stream
        System.setIn(in);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the main method
        ProgrammingHorse.main(new String[]{});

        // Verify that the output contains the expected messages
        String output = outContent.toString();
        assertTrue(output.contains("Round 1: "));
    }

    @Test
    void testMenuOption3() {
        // Define mock user input for menu selection
        String mockInput = "john\njames\n3\n"; // Simulate selecting option 3 (exit)
        ByteArrayInputStream in = new ByteArrayInputStream(mockInput.getBytes());

        // Redirect System.in to use the mock input stream
        System.setIn(in);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the main method
        ProgrammingHorse.main(new String[]{});

        // Verify that the output contains the expected messages
        String output = outContent.toString();
        assertTrue(output.contains("Closing game"));
    }
}
