
 package horse;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Random rng;
    private Player[] players;
    private Question currentQuestion;

    public Game(Player[] players) {
        this.rng = new Random();
        this.players = players;
    }

    public void playGame() {
        System.out.println("\nStarting Programming HORSE Game...");
        boolean hasWinner = false;

        // Reset players' points at the start
        for (Player player : players) {
            player.resetPoints();
        }

        while (!hasWinner) {
            playRound();
            hasWinner = checkWinCondition();
        }
    }

    private void playRound() {
        currentQuestion = new Question();
        // Assuming the range of IDs fits your database's question IDs
        currentQuestion.loadFromDataManager(rng.nextInt(10) + 1); // Load a question with a random ID
        currentQuestion.displayQuestion();

        for (Player player : players) {
            int answer = getPlayerAnswer(player);
            if (answer == currentQuestion.getCorrectAnswer() + 1) { // Adjust based on 0 or 1 indexing
                player.updatePoints();
            }
        }

        // Display progress
        for (Player player : players) {
            System.out.println(player.getName() + "'s current score: " + player.getPoints());
            player.displayHORSE();
        }
    }

    private int getPlayerAnswer(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(player.getName() + ", choose your answer (1-4): ");
        int answer = scanner.nextInt();
        // Validate answer range
        return answer;
    }

    private boolean checkWinCondition() {
        for (Player player : players) {
            if (player.getPoints() == 5) { // Assuming 5 points to win
                System.out.println(player.getName() + " has won the game by spelling HORSE!");
                return true;
            }
        }
        return false;
    }
}
