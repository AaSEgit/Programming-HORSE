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

import main.Menu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class menuTests {

   @Test
    public void testDisplayMenu() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Call the method to be tested
            Menu menu = new Menu();
            menu.displayMenu();

            // Define the expected output
            String expectedOutput = """
                    Menu
                    -------------------------------
                    (1) View Rules
                    (2) Play Game
                    (3) Quit""";

            // Get the actual output
            String actualOutput = outContent.toString().trim();

            // Assert that the actual output matches the expected output
            assertEquals(expectedOutput, actualOutput);
        } finally {
            // Reset System.out
            System.setOut(System.out);
        }
    }

    @Test
    public void testGetMenuSelectionValidInput() {
        // Set up the input stream with a valid choice (2) and a newline character
        InputStream input = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(input);

        try {
            // Call the method to be tested
            Menu menu = new Menu();
            int userChoice = menu.getMenuSelection();

            // Assert that the actual user choice matches the expected user choice
            assertEquals(2, userChoice);
        } finally {
            // Reset System.in
            System.setIn(System.in);
        }
    }
   @Test
    public void testGetMenuSelectionInvalidInput() {
        // Set up the input stream with an invalid choice (0) and a newline character
        InputStream input = new ByteArrayInputStream("0\n".getBytes());
        System.setIn(input);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Call the method to be tested
            Menu menu = new Menu();
            int userChoice = menu.getMenuSelection();

            // Assert that the actual user choice matches the expected default value (0)
            assertEquals(0, userChoice);

            // Assert that the method displayed the "Invalid menu option." message
            assertTrue(outContent.toString().contains("Invalid menu option."));
        } finally {
            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }
}
