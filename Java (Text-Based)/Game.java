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
    Player[] players;
    Player currentPlayer;
    int[] playerAnswerSelections;
    Question currentQuestion;
    int numRounds;


    // TODO: complete Game constructor
    public Game(Player[] players) {
        this.players = players;
        playerAnswerSelections = new int[players.length];

        rng = new Random();

        numRounds = 0;
    }

    // TODO: Play multiple rounds until a Player wins the game
    public void gameLoop() {
        System.out.println("\nProgramming HORSE");
        System.out.println("-------------------------------");

        System.out.print("\nRound " + (numRounds+1) + ": ");
        playRound();
    }

    // TODO: Play multiple rounds until a Player wins the game
    public boolean playRound() {
        int answer;

        getQuestion();          // Load in a question
        System.out.println();   // print empty line

        // Get player responses
        for (int i = 0; i < players.length; i++) {
            answer = 0;
            while (answer == 0) {
                answer = getAnswerSelection(i);
            }
            playerAnswerSelections[i] = answer;
        }

        //TODO: compare player answers and update points

        return false;
    }

    // Load in a question
    void getQuestion() {
        currentQuestion = new Question();
        currentQuestion.loadQuestionFile(rng.nextInt(2));   // TODO: make more questions to expand rng range
        currentQuestion.displayQuestion();
    }

    // Prompt Player for answer choice and validate input
    public int getAnswerSelection(int i) {
        Scanner s = new Scanner(System.in);
        int answer = 0; // Default value, is returned if an exception is caught

        currentPlayer = players[i];
        try {
            while (answer == 0) {
                System.out.print(currentPlayer.getName() + ", what is your answer? ");
                answer = s.nextInt();   
                
                if (answer < 1 || answer > 4) {
                    answer = 0;
                    System.out.println("Invalid answer. Try again.");
                }
            }
        } catch (Exception e) {
            answer = 0;
            System.out.println("Invalid answer. Try again.");
        }
        
        return answer;
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
