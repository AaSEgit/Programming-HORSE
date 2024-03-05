/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 * Description:   This file contains the source code for the Player module.
 */

public class Player {
    String name;
    int points;

    /*
     * Constructor
     */
    public Player(String playerName) {
        name = playerName;
        points = 0;
    }

    // Returns the Player's name
    public String getName() {
        return name;
    }

    // Returns the number of points the Player has earned
    public int getPoints() {
        return points;
    }

    // Increments Player points
    void updatePlayerPoints() {
        ++points;
    }
}
