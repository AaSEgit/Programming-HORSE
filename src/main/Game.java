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
        currentQuestion.loadQuestionByID(1 + rng.nextInt(50));   // TODO: make more questions to expand rng range
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
        String[] choices = currentQuestion.getAnswerChoices();
        int player1answer = playerAnswerSelections[0] - 1;
        int player2answer = playerAnswerSelections[1] - 1;
        String correctAnswer = currentQuestion.getCorrectAnswer();

        String currentTopic = currentQuestion.getTopic();
        if (!players[0].topicScores.containsKey(currentTopic)){
            players[0].topicScores.put(currentTopic, new int[2]);
            players[1].topicScores.put(currentTopic, new int[2]);
        }

        if(choices[player1answer] == correctAnswer)
        {
            //increment correct answers
            players[0].topicScores.get(currentTopic)[0]++;
        }else{
            //increment wrong answer
            players[0].topicScores.get(currentTopic)[1]++;
        }

        if(choices[player2answer] == correctAnswer)
        {
            //increment correct answers
            players[1].topicScores.get(currentTopic)[0]++;
        }else{
            //increment wrong answer
            players[1].topicScores.get(currentTopic)[1]++;
        }


        // Compare answers in int[] playerAnswerSelections to determine the winner of the round (if any)
        // Use Player[] players to update the corresponding Player's points
        if (player1answer != player2answer) {
            if (choices[player1answer] == correctAnswer) {
                players[0].updatePoints();
            }
            else if (choices[player2answer] == correctAnswer)  {
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
                // show player 1 stats
                System.out.println("Player 1 Stats: ");   
                players[0].topicScores.forEach((key, value) 
                -> System.out.println("Topic: " + key + " Correct: " + value[0] + " Wrong " + value[1]));
                // show player 2 stats
                System.out.println("Player 2 Stats: "); 
                players[1].topicScores.forEach((key, value) 
                -> System.out.println("Topic: " + key + " Correct: " + value[0] + " Wrong " + value[1]));
                String[] playerNames = {players[0].getName(), players[1].getName()}; 
                //After win store all data in db
                DataManager.saveResponses(playerNames, players[0].topicScores, players[1].topicScores);
                // System.out.println("test worse topic" + DataManager.getWorstTopic(playerNames[0])); used to be tested to get the worse topic for player 1
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
