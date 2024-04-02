
/**
 *
 * @author gabri
 */
package horse;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private Map<String, Integer> playerNameToIdMap;
    private int nextPlayerId;

    public PlayerManager() {
        playerNameToIdMap = new HashMap<>();
        nextPlayerId = 1; // Initialize the next available player ID
    }

    // Register a new player and assign an ID
    public int registerPlayer(String playerName) {
        if (!playerNameToIdMap.containsKey(playerName)) {
            playerNameToIdMap.put(playerName, nextPlayerId);
            int assignedId = nextPlayerId;
            nextPlayerId++; // Prepare the next ID for future use
            return assignedId;
        } else {
            System.out.println("Player name already exists. Choose a different name.");
            return -1; // Return -1 to indicate failure
        }
    }

    // Get player ID by name
    public int getPlayerId(String playerName) {
        return playerNameToIdMap.getOrDefault(playerName, -1);
    }
    
    // Optionally, if you want to get a player's name by ID (useful in various game contexts)
    public String getPlayerName(int playerId) {
        for (Map.Entry<String, Integer> entry : playerNameToIdMap.entrySet()) {
            if (entry.getValue().equals(playerId)) {
                return entry.getKey();
            }
        }
        return null; // Or "Player not found" or similar, to indicate failure to find the player by ID
    }
}

    