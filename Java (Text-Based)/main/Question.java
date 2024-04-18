/*
 * CEN4025C - Software Engineering 2
 * Programmer: Ava Adams
 * Alicia Piedra
 * 
 * Git Repository: Programming-HORSE
 * Assignment: Capstone project prototype
 * Due Date: April 24, 2024
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Question {
    // Attributes
    private String topic;
    private String textPrompt;
    private String[] answerChoices;
    private String correctAnswer;

    // Methods
    /*
     * Constructor
     */
    public Question () {
        answerChoices = new String[4];
    }   

    // Loads a file from the question bank based on a Question ID
    public void loadQuestionByID(int ID) {
        try {
            String[] qNa = DataManager.loadQuestion(ID);
            
            topic = qNa[0];
            textPrompt = qNa[1];
            correctAnswer = qNa[2];
            
            List<Integer> randomIndices = new ArrayList<>();
        
            for (int i = 2; i <= 5; i++) {
                randomIndices.add(i);
            }
         Collections.shuffle(randomIndices);

             // Assign shuffled answers to answerChoices
            for (int i = 0; i < randomIndices.size(); i++) {
                answerChoices[i] = qNa[randomIndices.get(i)];
            }
        } catch (Exception e) {
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
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getTopic() { return topic; }

    public String getTextPrompt() { return textPrompt; }
    
    public String[] getAnswerChoices() {
        return answerChoices;
    }

}
