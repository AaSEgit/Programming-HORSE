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
import java.util.StringTokenizer;

public class Question {
    // Attributes
    Scanner fileScanner;
    String topic;
    String textPrompt;
    String[] answerChoices;
    int correctAnswer;

    // Methods
    public Question () {
        answerChoices = new String[4];
    }   

    public void loadQuestionFile(int ID) {
        try {
            // Create scanner to read file contents
            fileScanner = new Scanner(new File("question_" + ID + ".txt"));

            while (fileScanner.hasNextLine()) {
                topic = fileScanner.nextLine();
                textPrompt = fileScanner.nextLine();

                for (int i = 0; i < 4; i++) {
                    answerChoices[i] = fileScanner.nextLine();
                }

                correctAnswer = fileScanner.nextInt();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: The question file was not found");
        }
    }

    public void displayQuestion() {
        System.out.print(textPrompt + "\n");

        for (int i = 0; i < answerChoices.length; i++) {
            System.out.println("(" + (i+1) + ")  " + answerChoices[i]);
        }
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

}
