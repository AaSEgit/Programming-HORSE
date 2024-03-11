/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Question {
    // Attributes
    String topic;
    String textPrompt;
    String[] answerChoices;
    int correctAnswer;

    // Methods
    /*
     * Constructor
     */
    public Question () {
        answerChoices = new String[4];
    }   

    // Loads a file from the question bank based on a Question ID
    public void loadQuestionFile(int ID) {
        try {
            // Create scanner to read file contents
            Scanner fileScanner = new Scanner(new File("Java (Text-Based)/question_" + ID + ".txt"));

            while (fileScanner.hasNextLine()) {
                topic = fileScanner.nextLine();
                textPrompt = fileScanner.nextLine();

                for (int i = 0; i < answerChoices.length; i++) {
                    answerChoices[i] = fileScanner.nextLine();
                }

                correctAnswer = fileScanner.nextInt();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: The question file was not found");
        }
    }

    // Display Question
    public void displayQuestion() {
        System.out.print(textPrompt + "\n");

        for (int i = 0; i < answerChoices.length; i++) {
            System.out.println("(" + (i+1) + ")  " + answerChoices[i]);
        }
    }

    // Returns the correct answer for a question
    public int getCorrectAnswer() {
        return correctAnswer;
    }

}
