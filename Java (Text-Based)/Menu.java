/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 *  
 * Description:   This file contains the source code for the Menu module.
 */

import java.util.Scanner;

public class Menu {
    // Attributes
    private Scanner scanner;
    private boolean badData;

    // Methods
    /*
     * Displays main menu options
     */
    public void displayMenu() {
        System.out.println("\nMenu");
        System.out.println("-------------------------------");
        System.out.println("(1) View Rules");
        System.out.println("(2) Play Game");
        System.out.println("(3) Quit");
    }

    public int getMenuSelection() {
        scanner = new Scanner(System.in);
        badData = true;
        int userChoice = 0;     // Default value, is returned if an exception is caught

        try {
            while(badData) {
                displayMenu();
                    System.out.print("Make a selection: ");
                    userChoice = scanner.nextInt();
    
                    if (userChoice < 1 || userChoice > 3) {
                        badData = true;
                        System.out.println("Invalid menu option.");
                    }
                    else {
                        badData = false;
                    }
            }
        } catch (Exception e) {
            System.out.println("Error: not a valid integer");
        }

        return userChoice;
    }
}
