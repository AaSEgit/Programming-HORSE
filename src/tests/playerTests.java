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

import main.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class playerTests {

    @Test
    void testGetName() {
        // Create a player
        Player player = new Player("TestPlayer");

        // Call the method to be tested
        String playerName = player.getName();

        // Assert that the correct player name is returned
        assertEquals("TestPlayer", playerName);
    }

    @Test
    void testGetPoints() {
        // Create a player
        Player player = new Player("TestPlayer");

        // Call the method to be tested
        int initialPoints = player.getPoints();

        // Assert that the initial points are 0
        assertEquals(0, initialPoints);
    }

    @Test
    void testDisplayHORSE() {
        // Create a player
        Player player = new Player("TestPlayer");

        // Set up player points for testing
        player.setPoints(3);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Call the method to be tested
        player.displayHORSE();

        // Reset System.out to the original
        System.setOut(System.out);

        // Assert that the correct letters are displayed
        assertEquals("HOR__", outputStreamCaptor.toString().trim());
    }

    @Test
    void testUpdatePlayerPoints() {
        // Create a player
        Player player = new Player("TestPlayer");

        // Call the method to be tested
        player.setPoints(2);

        // Assert that the player points are updated correctly
        assertEquals(2, player.getPoints());
    }

    @Test
    void testResetPoints() {
        // Create a player
        Player player = new Player("TestPlayer");

        // Set up player points for testing
        player.setPoints(3);

        // Call the method to be tested
        player.resetPoints();

        // Assert that the player points are reset to 0
        assertEquals(0, player.getPoints());
    }
}
