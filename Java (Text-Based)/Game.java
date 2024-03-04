/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 */

import java.util.Random;
import java.util.Scanner;

public class Game {
    Random rng;
    Question[] questionBank;
    Player[] players;

    // TODO: complete Game constructor
    public Game(Player[] players) {
        this.players = players;
    }

    // TODO: Play multiple rounds until a Player wins the game
    public void gameLoop() {
        System.out.println("\nProgramming HORSE");
        System.out.println("-------------------------------");
        System.out.println("TODO: Play Game...");
    }

    // TODO: Play multiple rounds until a Player wins the game
    public boolean playRound() {
        return false;
    }

    // TODO: Prompt Player for answer choice and validate input
    public int getAnswerSelection() {
        return 0;
    }

    /*
     * TODO: Check if a Player has won the game at the end of a round
     * Return the index of the Player who won
     * Otherwise, return -1
     */
    public int checkWinCondition() {
        return -1;  // no Players have won the game yet
    }
}
