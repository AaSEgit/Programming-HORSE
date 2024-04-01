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

public class Question {
    private String topic;
    private String textPrompt;
    private String[] answerChoices;
    private int correctAnswer;

    // Constructor
    public Question() {
        this.answerChoices = new String[4];
    }

    // Load question data from DataManager
    public void loadFromDataManager(int questionID) {
        Question loadedQuestion = DataManager.loadQuestion(questionID);
        this.topic = loadedQuestion.getTopic();
        this.textPrompt = loadedQuestion.getTextPrompt();
        this.answerChoices = loadedQuestion.getAnswerChoices();
        this.correctAnswer = loadedQuestion.getCorrectAnswer();
    }

    // Display the question and its choices
    public void displayQuestion() {
        System.out.println(textPrompt);
        for (int i = 0; i < answerChoices.length; i++) {
            System.out.println("(" + (i + 1) + ") " + answerChoices[i]);
        }
    }

    // Getters and Setters
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTextPrompt() {
        return textPrompt;
    }

    public void setTextPrompt(String textPrompt) {
        this.textPrompt = textPrompt;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
