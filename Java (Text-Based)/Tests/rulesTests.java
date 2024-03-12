package Tests;
import main.Rules;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class rulesTests {

    @Test
    void testDisplayRules() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Set up the input stream with a newline character
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream("\n".getBytes()));

        try {
            // Call the method to be tested
            Rules rules = new Rules();
            rules.displayRules();

            // Define the expected output
            String expectedOutput = "Rules Overview\n" +
                    "-------------------------------\n" +
                    "Welcome to Programming HORSE, a game that teaches early coding concepts!" +
                    "\nTo start a game of Programming HORSE, select \"(2) Play Game\" at the main menu." +
                    "\nEach round, two players will take turns answering a question based on an introductory programming " +
                    "\ntopic. Use your keyboard to enter your selection at the message prompt." +

                    "\n\nIf both players answer correctly, the round results in a tie, and no letters are rewarded." +
                    "\nOtherwise, the sole player who answers correctly will earn a letter towards spelling H-O-R-S-E. " +
                    "\nThe player who earns all five (5) letters first wins the game!" +

                    "\n\nSaving your study guide\n" +
                    "-------------------------------\n" +
                    "At the end of a game of Programming HORSE, the program will create a custom study guide " +
                    "\nbased on each player's in-game performance. The study guide will provide the player's strongest " +
                    "\nand weakest topics, and recommend topics to study before the next game." +

                    "\n\nNOTE: Players will not receive a study guide for an unfinished game." +
                    "\nNOTE: Discarding a study guide means it will no longer be available to download." +

                    "\n\nPress ENTER to return to main menu:";

            // Get the actual output
            String actualOutput = outputStreamCaptor.toString().trim();

            // Assert that the actual output matches the expected output
            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.in and System.out
            System.setIn(originalIn);
            System.setOut(System.out);
        }
    }
}
