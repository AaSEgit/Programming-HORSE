package Tests;

import main.Question;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class questionTests {
    private Question question;

    @Before
    public void setUp() {
        question = new Question();
    }

    @Test
    public void testLoadQuestionFile() {
        question.loadQuestionFile(1);

        assertEquals("Expected topic does not match", "Data Types", question.topic);
        assertEquals("Expected text prompt does not match", "Which data type is used for storing whole number values?", question.textPrompt);

        String[] expectedChoices = {"float/double", "integer", "boolean", "character"};
        assertArrayEquals("Expected answer choices do not match", expectedChoices, question.answerChoices);

        assertEquals("Expected correct answer does not match", 1, question.getCorrectAnswer());
    }

    @Test
    public void testLoadNonExistentQuestionFile() {
        // Assuming there is no question with ID 1000
        question.loadQuestionFile(1000);

        assertNull("Topic should be null for a non-existent file", question.topic);
        assertNull("Text prompt should be null for a non-existent file", question.textPrompt);
        assertNull("Answer choices should be null for a non-existent file", question.answerChoices);
        assertEquals("Correct answer should be -1 for a non-existent file", -1, question.getCorrectAnswer());
    }

    @Test
    public void testDisplayQuestion() {
        // Set up a test question
        question.loadQuestionFile(1); // Assuming loadQuestionFile correctly loads the data

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call displayQuestion method
        question.displayQuestion();

        // Reset System.out normal
        System.setOut(System.out);

        // Check the printed output
        String expectedOutput = "Which data type is used for storing whole number values?\n(1)  float/double\n(2)  integer\n(3)  boolean\n(4)  character\n";
        assertEquals("Display output does not match expected output", expectedOutput, outContent.toString());
    }
}
