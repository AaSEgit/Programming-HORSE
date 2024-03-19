package Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.Question;
import main.Player;
import main.Game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class gameTests {

   @Test
    void testConstructor() {
        // Test the constructor to ensure it initializes attributes correctly
        Player[] players = {new Player("Player1"), new Player("Player2")};
        Game game = new Game(players);
        assertNotNull(game);
        assertEquals(0, game.getNumRounds());
        assertNotNull(game.getPlayers());
        assertEquals(2, game.getPlayers().length);
        assertNotNull(game.getPlayerAnswerSelections());
        assertEquals(2, game.getPlayerAnswerSelections().length);
    }


    @Test
    void testGetAnswerSelection_Player1ValidAnswer() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the mocked players
        Game game = new Game(players);

        // Mock user input for player 1 (valid answer)
        ByteArrayInputStream in1 = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in1);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call getAnswerSelection method for player 1
        int answer1 = game.getAnswerSelection(0);

        // Reset System.out and System.in
        System.setOut(System.out);
        System.setIn(System.in);

        // Verify that player 1 selected a valid answer
        assertEquals(2, answer1);

        // Verify the printed messages
        assertTrue(outContent.toString().contains("Player1, what is your answer? "));
        assertFalse(outContent.toString().contains("Player2, what is your answer? ")); // Ensure player 2's prompt is not shown
        assertFalse(outContent.toString().contains("Invalid answer. Try again.")); // Ensure invalid answer message is not shown
    }

    @Test
    void testGetAnswerSelection_Player2InvalidThenValidAnswer() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the mocked players
        Game game = new Game(players);

        // Mock user input for player 2 (invalid answer, then valid answer)
        ByteArrayInputStream in2 = new ByteArrayInputStream("0\n3\n".getBytes());
        System.setIn(in2);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call getAnswerSelection method for player 2
        int answer2 = game.getAnswerSelection(1);

        // Reset System.out and System.in
        System.setOut(System.out);
        System.setIn(System.in);

        // Verify that player 2 selected a valid answer after entering an invalid one
        assertEquals(3, answer2);

        // Verify the printed messages
        assertFalse(outContent.toString().contains("Player1, what is your answer? ")); // Ensure player 1's prompt is not shown
        assertTrue(outContent.toString().contains("Player2, what is your answer? "));
        assertTrue(outContent.toString().contains("Invalid answer. Try again.")); // Ensure invalid answer message is shown
    }

    @Test
    void testCheckWinCondition_Player1Wins() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the mocked players
        Game game = new Game(players);

        // Test case: Player 1 wins
        player1.updatePlayerPoints(5);
        assertEquals(0, game.checkWinCondition()); // Player 1 should win
    }

    @Test
    void testCheckWinCondition_NoWinner() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the mocked players
        Game game = new Game(players);

        // Test case: No winner yet
        player2.updatePlayerPoints(3); // Player 2 has 3 points
        assertEquals(-1, game.checkWinCondition()); // No winner yet
    }
  
}
