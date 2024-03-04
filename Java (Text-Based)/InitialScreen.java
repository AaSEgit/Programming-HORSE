/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 * Description:   This file contains the source code for the InitialScreen module.
 */

import java.util.Scanner;

public class InitialScreen {

    // Attributes
    private Scanner scanner;    // Scanner object to receive user input
    private boolean badData;    // Used to validate user input

    // Methods
    /*
     * Constructor
     * Displays welcome message
     */
    public InitialScreen() {
        System.out.println("\nWelcome to Programming HORSE!");
        System.out.println("-------------------------------");
    }

    // Get the name of player 1 and validate user input
    public String getPlayer1Name() {
        scanner = new Scanner(System.in);
        badData = true;
        String p1Name = "Player1";

        while (badData) {
            System.out.print("Enter the name of player 1: ");
            p1Name = scanner.nextLine();

            if (nameIsCorrectLength(p1Name) == false) {
                badData = true;
                System.out.println("Invalid entry. Player names must be between 1 and 20 characters long.");
            }
            else {
                badData = false;
            }
        }
        return p1Name;
    }

    // Get the name of player 2 and validate user input
    public String getPlayer2Name(String p1Name) {
        scanner = new Scanner(System.in);
        badData = true;
        String p2Name = "Player2";

        while (badData) {
            System.out.print("Enter the name of player 2: ");
            p2Name = scanner.nextLine();

            if (nameIsCorrectLength(p2Name) == false) {
                badData = true;
                System.out.println("Invalid entry. Player names must be between 1 and 20 characters long.");
            }
            else if (p1Name.compareTo(p2Name) == 0) {
                badData = true;
                System.out.println("Invalid entry. Player 1 and 2 cannot have the same name.");
            }
            else {
                badData = false;
            }
        }
        return p2Name;
    }

    // Checks if the Player's name is the correct length
    boolean nameIsCorrectLength(String pName) {
        if (pName.length() >= 3 && pName.length() <= 20) {
            return true;
        }
        return false;
    }
}
