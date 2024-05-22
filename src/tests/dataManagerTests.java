/*
 * CEN4025C - Software Engineering 2
 * Programmers: Ava Adams, Juan Leon Perez
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 * Description:   This file contains the main method for the Programming HORSE game.
 *                  The user will run this file to play the game.
 */

package tests;

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
