/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * working on stracting th data from SQL
 * @author gabri
 */
package horse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/C:\\\\xampp\\\\mysql\\\\data\\\\horsedb";
    private static final String USER = "root";
    private static final String PASS = "  ";

    // Load question from database
    public static Question loadQuestion(int questionID) {
        Question question = new Question();
        
        // SQL query to fetch a question based on ID
        String query = "SELECT topic, textPrompt, choice1, choice2, choice3, choice4, correctAnswer FROM questions WHERE id = ?";

        // Try-with-resources statement to auto-close resources
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, questionID);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    question.setTopic(rs.getString("topic"));
                    question.setTextPrompt(rs.getString("textPrompt"));
                    question.setAnswerChoices(new String[]{
                        rs.getString("choice1"),
                        rs.getString("choice2"),
                        rs.getString("choice3"),
                        rs.getString("choice4")
                    });
                    question.setCorrectAnswer(rs.getInt("correctAnswer") - 1); // Adjust based on how you index answers (0-based or 1-based)
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return question;
    }
}
