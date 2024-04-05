/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 * Description:   This file contains the main method for the Programming HORSE game.
 *                  The user will run this file to play the game.
 */

public class ProgrammingHorse {

    public static void main (String[] args) {
        Question test = DataManager.loadQuestion(1);

        final int NUM_PLAYERS = 2;
        Player[] players = new Player[NUM_PLAYERS];
        int menuSelection;
        
        // Create InitialScreen object
        InitialScreen initialScreen = new InitialScreen();

        // Get player names and initialize Player objects
        players[0] = new Player(initialScreen.getPlayer1Name());
        players[1] = new Player(initialScreen.getPlayer2Name(players[0].getName()));
        
        // Create Menu object and get menu selection
        Menu menu = new Menu();

        while (true) {
            menuSelection = 0;
            while (menuSelection == 0) {
                menuSelection = menu.getMenuSelection();
            }

            if (menuSelection == 1) {
                Rules rules = new Rules();
                rules.displayRules();
            }
            else if (menuSelection == 2) {
                Game game = new Game(players);
                game.playGame();
            }
            else if (menuSelection == 3) {
                System.out.println("\nClosing game...Goodbye.\n");
                System.exit(0);
            }
        }
    }
}