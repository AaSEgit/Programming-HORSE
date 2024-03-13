package Tests;

import main.InitialScreen;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class initialScreenTests {

    @Test
    public void testGetPlayer1NameValidInput() {
        // Set up the input stream with a valid player name and a newline character
        InputStream input = new ByteArrayInputStream("PlayerOne\n".getBytes());
        System.setIn(input);

        try {
            // Call the method to be tested
            InitialScreen initialScreen = new InitialScreen();
            String player1Name = initialScreen.getPlayer1Name();

            // Assert that the actual player name matches the expected player name
            assertEquals("PlayerOne", player1Name);
        } finally {
            // Reset System.in
            System.setIn(System.in);
        }
    }

    @Test
    public void testGetPlayer2NameValidInput() {
        // Set up the input stream with a valid player name different from player 1 and a newline character
        InputStream input = new ByteArrayInputStream("PlayerTwo\n".getBytes());
        System.setIn(input);

        try {
            // Call the method to be tested
            InitialScreen initialScreen = new InitialScreen();
            String player1Name = "PlayerOne";
            String player2Name = initialScreen.getPlayer2Name(player1Name);

            // Assert that the actual player name matches the expected player name
            assertEquals("PlayerTwo", player2Name);
        } finally {
            // Reset System.in
            System.setIn(System.in);
        }
    }

    @Test
    public void testGetPlayer1NameInvalidLengthThenValidInput() {
        // Set up the input stream with an invalid player name (less than min length) followed by a valid one
        InputStream input = new ByteArrayInputStream("ab\nPlayerOne\n".getBytes());
        System.setIn(input);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Call the method to be tested
            InitialScreen initialScreen = new InitialScreen();
            String player1Name = initialScreen.getPlayer1Name();

            // Assert that the actual player name matches the expected player name
            assertEquals("PlayerOne", player1Name);

            // Assert that the method displayed the correct error message
            assertTrue(outContent.toString().contains("Invalid entry. Player names must be between 3 and 20 characters long."));
        } finally {
            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }

    @Test
    public void testGetPlayer2NameInvalidLengthThenValidInput() {
        // Set up the input stream with an invalid player name (greater than max length), then a valid one different from player 1
        InputStream input = new ByteArrayInputStream("PlayerTwoPlayerTwoPlayerTwo\nPlayerTwo\n".getBytes());
        System.setIn(input);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Call the method to be tested
            InitialScreen initialScreen = new InitialScreen();
            String player1Name = "PlayerOne";
            String player2Name = initialScreen.getPlayer2Name(player1Name);

            // Assert that the actual player name matches the expected player name
            assertEquals("PlayerTwo", player2Name);

            // Assert that the method displayed the correct error message
            assertTrue(outContent.toString().contains("Invalid entry. Player names must be between"));
        } finally {
            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }

    @Test
    public void testGetPlayer2NameSameNameAsPlayer1() {
        // Set up the input stream with a player name that is the same as player 1
        InputStream input = new ByteArrayInputStream("PlayerOne\nPlayerTwo\n".getBytes());
        System.setIn(input);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            // Call the method to be tested
            InitialScreen initialScreen = new InitialScreen();
            String player1Name = "PlayerOne";
            String player2Name = initialScreen.getPlayer2Name(player1Name);

            // Assert that the actual player name matches the expected player name
            assertEquals("PlayerTwo", player2Name);

            // Assert that the method displayed the correct error message
            assertTrue(outContent.toString().contains("Invalid entry. Player 1 and 2 cannot have the same name."));
        } finally {
            // Reset System.in and System.out
            System.setIn(System.in);
            System.setOut(System.out);
        }
    }
}
