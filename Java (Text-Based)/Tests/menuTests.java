package Tests;
import main.Menu;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class menuTests {

    @Test
    void testGetMenuSelection() {
        // Redirect System.out and System.in to capture the output and simulate user input
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Set up the input stream with a sequence of characters and a newline character
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        try {
            // Call the method to be tested
            Menu menu = new Menu();
            int userChoice = menu.getMenuSelection();

            // Define the expected output and user choice
            String expectedOutput = "Menu\n" +
                    "-------------------------------\n" +
                    "(1) View Rules\n" +
                    "(2) Play Game\n" +
                    "(3) Quit\n" +
                    "Make a selection: ";
            int expectedUserChoice = 2;

            // Get the actual output
            String actualOutput = outputStreamCaptor.toString().trim();

            // Assert that the actual output matches the expected output
            assertEquals(expectedOutput, actualOutput);

            // Assert that the actual user choice matches the expected user choice
            assertEquals(expectedUserChoice, userChoice);
        } finally {
            // Reset System.in and System.out
            System.setIn(originalIn);
            System.setOut(System.out);
        }
    }
}
