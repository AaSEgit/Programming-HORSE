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
        player1.setPoints(5);
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
        player2.setPoints(3); // Player 2 has 3 points
        assertEquals(-1, game.checkWinCondition()); // No winner yet
    }

    @Test
    void testDetermineRound_Player1Wins() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the players
        Game game = new Game(players);

        // Set up player answer selections and current question for testing
        game.setPlayerAnswerSelections(new int[]{1, 0}); // Player 1 wins this round
        Question question = new Question();
        question.loadQuestionFile(1); // Correct answer index is 0
        game.setCurrentQuestion(question);

        // Call determineRound method
        game.determineRound();

        // Verify that player 1's points are updated
        assertEquals(1, player1.getPoints());
        // Verify that player 2's points remain unchanged
        assertEquals(0, player2.getPoints());
    }

    @Test
    void testDetermineRound_Player2Wins() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the players
        Game game = new Game(players);

        // Set up player answer selections and current question for testing
        game.setPlayerAnswerSelections(new int[]{3, 1}); // Player 2 wins this round
        Question question = new Question();
        question.loadQuestionFile(0); // Correct answer index is 0
        game.setCurrentQuestion(question);

        // Call determineRound method
        game.determineRound();

        // Verify that player 2's points are updated
        assertEquals(1, player2.getPoints());
        // Verify that player 1's points remain unchanged
        assertEquals(0, player1.getPoints());
    }

    @Test
    void testDetermineRound_Tie() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the players
        Game game = new Game(players);

        // Set up player answer selections and current question for testing
        game.setPlayerAnswerSelections(new int[]{2, 2}); // Tie this round
        Question question = new Question();
        question.loadQuestionFile(1); // Correct answer index is 1
        game.setCurrentQuestion(question);

        // Call determineRound method
        game.determineRound();

        // Verify that no player's points are updated
        assertEquals(0, player1.getPoints());
        assertEquals(0, player2.getPoints());
    }

   @Test
    void testGetQuestion() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create a game
        Game game = new Game(new Player[]{});

        // Call getQuestion method
        game.getQuestion();

        // Reset System.out
        System.setOut(System.out);

        // Split the captured output into lines
        String[] lines = outContent.toString().split("\\r?\\n");

        // Verify that the output contains 5 lines of text corresponding to how question is displayed
        assertEquals(5, lines.length);
    }

   //FIX: Running into infinite loop when prompting input for Player2 
   @Test
    void testPlayRound() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the players
        Game game = new Game(players);


        // Define mock user input for players
        String mockInput = "2\n3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(mockInput.getBytes());

        // Redirect System.in to use the mock input stream
        System.setIn(in);
        

        // Call playRound method
        game.playRound();

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        // Verify that the output contains the expected messages for each step of the round
        String output = outContent.toString();
        assertTrue(output.contains("Round 1: "));
        assertTrue(output.contains("Which data type is used for storing whole number values?"));
        assertTrue(output.contains("Player1, what is your answer? "));
        assertTrue(output.contains("Player2, what is your answer? "));
        assertTrue(output.contains("Player1: _ _ _ _ _"));
        assertTrue(output.contains("Player2: _ _ _ _ _"));
    }

   @Test
    void testPlayGame() {
        // Create players for testing
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Player[] players = {player1, player2};

        // Create a game with the players
        Game game = new Game(players);

        // Define mock user input for players
        String mockInput = "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n"; // Simulate valid answers for each round
        ByteArrayInputStream in = new ByteArrayInputStream(mockInput.getBytes());

        // Redirect System.in to use the mock input stream
        System.setIn(in);

        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call playGame method
        game.playGame();

        // Verify the output contains the expected messages for each step of the game
        String output = outContent.toString();
        assertTrue(output.contains("Programming HORSE"));
        assertTrue(output.contains("Round 1: "));
        assertTrue(output.contains("Round 2: "));
        assertTrue(output.contains("Player1 spelled HORSE! You win!"));
    }
}
