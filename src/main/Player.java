/*
 * CEN4025C - Software Engineering 2
 * Programmers: Ava Adams, Juan Leon Perez, Alicia Piedra, Gabriel Perez Alpizar
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 * Description:   This file contains the main method for the Programming HORSE game.
 *                  The user will run this file to play the game.
 */

package main;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private int points;
    private char[] currentLetters;
    private static char[] HORSE = {'H', 'O', 'R', 'S', 'E'};
    public Map<String, int[]> topicScores;

    /*
     * Constructor
     */
    public Player(String playerName) {
        name = playerName;
        topicScores = new HashMap<>();
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

    // Displays the Player’s progress towards spelling HORSE 
    //based on the points counter
    public void displayHORSE() {
        // Display player name
        System.out.print(name + ": ");

        // Update currentLetters based on points counter
        if (points > 0) {
            currentLetters[points - 1] = HORSE[points - 1];
        }

        // Display currentLetters
        for (char l : currentLetters) {
            System.out.print(l);
        }
        System.out.println();
    }

    // Increments Player points
    public void updatePoints() {
        points++;
    }

    // Reset points counter and currentLetters
    public void resetPoints() {
        points = 0;
        currentLetters = new char[] {'_', '_', '_', '_', '_'};
    }

    // Set points counter
    public void setPoints(int p) {
        points = p;
    }
}
