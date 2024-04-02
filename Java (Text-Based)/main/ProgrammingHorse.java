/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package horse;

/**
 *
 * @author gabri
 */
public class ProgrammingHorse {
    public static void main(String[] args) {
        final int NUM_PLAYERS = 2;
        Player[] players = new Player[NUM_PLAYERS];
        int menuSelection;

        // Create PlayerManager
        PlayerManager playerManager = new PlayerManager();

     

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

