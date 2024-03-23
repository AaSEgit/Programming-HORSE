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
    // Attributes
    private Random rng;         // Random number generator
    private int numRounds;
    private Player[] players;
    private int[] playerAnswerSelections;
    private Player currentPlayer;
    private Question currentQuestion;

    // Methods
    /*
     * Constructor
     */
    public Game(Player[] players) {
        rng = new Random();
        numRounds = 0;
        this.players = players;
        playerAnswerSelections = new int[players.length];
    }

    // Play multiple rounds until a Player wins the game
    public void playGame() {
        int winner = -1;

        for (Player p : players) {
            p.resetPoints();
        }

        System.out.println("\nProgramming HORSE");
        System.out.println("-------------------------------");

        while (winner == -1) {
            playRound();                    // play a single round
            winner = checkWinCondition();   // check if any Players won yet
        }
    }

    // Play a single round
    public void playRound() {
        int answer;

        System.out.print("\nRound " + (numRounds+1) + ": ");

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

        // Determine the winner of the round
        determineRound();

        // Display Player progress
        for (Player p : players) {
            p.displayHORSE();
        }

        // Update round counter
        numRounds++;
    }

    // Load in a randomized Question
    public void getQuestion() {
        currentQuestion = new Question();
        currentQuestion.loadQuestionFile(rng.nextInt(2));   // TODO: make more questions to expand rng range
        currentQuestion.displayQuestion();
    }

    // Prompt Player for answer choice
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
 
    // Compare player answers and update points
    public void determineRound() {
        // Compare answers in int[] playerAnswerSelections to determine the winner of the round (if any)
        // Use Player[] players to update the corresponding Player's points
        if (playerAnswerSelections[0] != playerAnswerSelections[1]) {
            if (playerAnswerSelections[0] == currentQuestion.getCorrectAnswer() + 1) {
                players[0].updatePoints();
            }
            else {
                players[1].updatePoints();
            }
        }
    }

    /*
     * Check if a Player has won the game at the end of a round (has 5 points)
     * Return the index of the Player who won
     * Otherwise, return -1
     */
    public int checkWinCondition() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getPoints() == 5) {
                System.out.println("\n" + players[i].getName() + " spelled HORSE! You win!");
                return i;
            }
        }
        return -1;  // no Players have won the game yet
    }

    /*
     * Getter methods 
     */
    public int getNumRounds() {
        return numRounds;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int[] getPlayerAnswerSelections() {
        return playerAnswerSelections;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setPlayerAnswerSelections(int[] playerAnswerSelections) {
        this.playerAnswerSelections = playerAnswerSelections;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}
