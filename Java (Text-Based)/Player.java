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
    private String name;
    private int points;
    private char[] currentLetters;
    private static char[] HORSE = {'H', 'O', 'R', 'S', 'E'};

    /*
     * Constructor
     */
    public Player(String playerName) {
        name = playerName;
        resetPoints();
    }

    // Returns the Player's name
    public String getName() {
        return name;
    }

    // Returns the number of points the Player has earned
    public int getPoints() {
        return points;
    }

    public void displayHORSE() {
        if (points > 0) {
            for (int i = 0; i < points; i++) {
                currentLetters[i] = HORSE[i];
            }
        }

        for (char l : currentLetters) {
            System.out.print(l);
        }
    }

    // Increments Player points
    void updatePlayerPoints(int p) {
        points += p;
    }

    // 
    public void resetPoints() {
        points = 0;
        currentLetters = new char[] {'_', '_', '_', '_', '_'};
    }
}
