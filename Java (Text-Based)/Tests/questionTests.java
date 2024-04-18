package Tests;

import main.Question;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class questionTests {
    
    @Test
    void testLoadQuestionByID() {
        // Test loading a question with ID 1
        Question question = new Question();
        question.loadQuestionByID(1);

        // Verify that the question topic is not null or empty
        assertNotNull(question.getTopic());
        assertNotEquals("", question.getTopic());

        // Verify that the text prompt is not null or empty
        assertNotNull(question.getTextPrompt());
        assertNotEquals("", question.getTextPrompt());

        // Verify that the correct answer is not null or empty
        assertNotNull(question.getCorrectAnswer());
        assertNotEquals("", question.getCorrectAnswer());

        // Verify that the answer choices array is not null and has exactly 4 elements
        assertNotNull(question.getAnswerChoices());
        assertEquals(4, question.getAnswerChoices().length);

        // Verify that each answer choice is not null or empty
        for (String answer : question.getAnswerChoices()) {
            assertNotNull(answer);
            assertNotEquals("", answer);
        }
    }

    @Test
    void testDisplayQuestion() {
        // Test displaying a question
        Question question = new Question();
        question.loadQuestionByID(1);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call displayQuestion method
        question.displayQuestion();

        // Reset System.out
        System.setOut(System.out);

        // Verify that the output contains the text prompt and answer choices
        String output = outContent.toString();
        assertTrue(output.contains(question.getTextPrompt()));
        for (String answer : question.getAnswerChoices()) {
            assertTrue(output.contains(answer));
        }
    }

    @Test
    void testGetCorrectAnswer() {
        // Test getting the correct answer
        Question question = new Question();
        question.loadQuestionByID(1);

        // Verify that the correct answer matches one of the answer choices
        String correctAnswer = question.getCorrectAnswer();
        assertNotNull(correctAnswer);
        assertTrue(List.of(question.getAnswerChoices()).contains(correctAnswer));
    }
}
