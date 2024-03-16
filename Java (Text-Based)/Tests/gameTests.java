package Tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.Question;
import main.Player;
import main.Game;

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
  
}
