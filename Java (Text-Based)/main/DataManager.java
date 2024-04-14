/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author gabri
 */
// package horse;

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
    public static String[] loadQuestion(int questionID) {
        String query = "SELECT question, answer FROM questions WHERE question_id = ?";
        String randomAnswersQuery = "SELECT answer FROM questions WHERE question_id != ? ORDER BY RAND() LIMIT 3";
        
        String[] questionAndAnswers = new String[5]; 
    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             PreparedStatement pstmtRandom = conn.prepareStatement(randomAnswersQuery)) {
    
            // First, get the specific question and its correct answer
            pstmt.setInt(1, questionID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    questionAndAnswers[0] = rs.getString("question");
                    questionAndAnswers[1] = rs.getString("answer");
                  //  System.out.println(questionAndAnswers[0]);
                }
            }
    
            pstmtRandom.setInt(1, questionID);
            try (ResultSet rs = pstmtRandom.executeQuery()) {
                int index = 2; //starting at this index since 0,1 hold the correct question and answer
                while (rs.next() && index < 5) {
                    ///System.out.println("did this work: ->  " + rs.getString("answer") + "\n");
                    questionAndAnswers[index++] = rs.getString("answer");
                }
            }
    
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    
        return questionAndAnswers;
    }
}
