package Tests;

import main.DataManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class dataManagerTests {

    @Test
    void testLoadQuestion() {
        // Test loading a question with ID 1
        String[] questionAndAnswers = DataManager.loadQuestion(1);

        // Verify that the returned array is not null
        assertNotNull(questionAndAnswers);

        // Verify that the array has exactly 5 elements (question, correct answer, and 3 random answers)
        assertEquals(5, questionAndAnswers.length);

        // Verify that the question is not null or empty
        assertNotNull(questionAndAnswers[0]);
        assertNotEquals("", questionAndAnswers[0]);

        // Verify that the correct answer is not null or empty
        assertNotNull(questionAndAnswers[1]);
        assertNotEquals("", questionAndAnswers[1]);

        // Verify that each random answer is not null or empty
        for (int i = 2; i < questionAndAnswers.length; i++) {
            assertNotNull(questionAndAnswers[i]);
            assertNotEquals("", questionAndAnswers[i]);
        }
    }
}
