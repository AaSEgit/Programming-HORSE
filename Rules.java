/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 */
package horse;
import java.util.Scanner;

public class Rules {

    public void displayRules() {
        Scanner s = new Scanner(System.in);

        System.out.println("\nRules Overview");
        System.out.println("-------------------------------");
        System.out.println("Welcome to Programming HORSE, a game that teaches early coding concepts!" + 
                            "\nTo start a game of Programming HORSE, select \"(2) Play Game\" at the main menu." + 
                            "\nEach round, two players will take turns answering a question based on an introductory programming " +
                            "\ntopic. Use your keyboard to enter your selection at the message prompt." +

                            "\n\nIf both players answer correctly, the round results in a tie, and no letters are rewarded." + 
                            "\nOtherwise, the sole player who answers correctly will earn a letter towards spelling H-O-R-S-E. " + 
                            "\nThe player who earns all five (5) letters first wins the game!");

        System.out.println("\nSaving your study guide");
        System.out.println("-------------------------------");
        System.out.println("At the end of a game of Programming HORSE, the program will create a custom study guide " +
                            "\nbased on each player's in-game performance. The study guide will provide the player's strongest " +
                            "\nand weakest topics, and recommend topics to study before the next game." + 

                            "\n\nNOTE: Players will not receive a study guide for an unfinished game." +
                            "\nNOTE: Discarding a study guide means it will no longer be available to download.");

        System.out.print("\nPress ENTER to return to main menu: ");
        s.nextLine();
    }
}
