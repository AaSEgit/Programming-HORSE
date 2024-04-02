/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gabri
 */
package horse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataManager {
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Print the stack trace for debugging.
        }
    }
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/horsedb";
    private static final String USER = "root";
    private static final String PASS = "";

    // Method adjusted for the provided table structure
    public static Question loadQuestion(int questionID) {
        Question question = new Question();
        
        // Adjusted SQL query to match the provided table structure
        String query = "SELECT question FROM questions WHERE question_Id = ?";
 
        // Try-with-resources statement to auto-close resources
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, questionID);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Assuming 'question' is a field in your Question class that stores the question text
                    question.setTextPrompt(rs.getString("question"));

                    // As there's no detail on choices or correct answers, those parts are omitted
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

        return question;
    }
}
