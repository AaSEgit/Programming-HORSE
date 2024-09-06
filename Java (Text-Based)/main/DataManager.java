/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Alicia Piedra
 * Programmer: Gabriel
 */
// package horse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;


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
        String query = "SELECT question, answer, topic FROM questions WHERE question_id = ?";
        String randomAnswersQuery = "SELECT answer FROM questions WHERE question_id != ? ORDER BY RAND() LIMIT 3";
        
        String[] questionAndAnswers = new String[6]; 
    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             PreparedStatement pstmtRandom = conn.prepareStatement(randomAnswersQuery)) {
    
            // First, get the specific question and its correct answer
            pstmt.setInt(1, questionID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    questionAndAnswers[0] = rs.getString("topic");
                    questionAndAnswers[1] = rs.getString("question");
                    questionAndAnswers[2] = rs.getString("answer"); 
                  //  System.out.println(questionAndAnswers[0]);
                }
            }
    
            pstmtRandom.setInt(1, questionID);
            try (ResultSet rs = pstmtRandom.executeQuery()) {
                int index = 3; //starting at this index since 0,1,2 hold the correct question and answer
                while (rs.next() && index < 6) {
                    ///System.out.println("did this work: ->  " + rs.getString("answer") + "\n");
                    questionAndAnswers[index++] = rs.getString("answer");
                }
            }
    
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
         //System.out.println(Arrays.toString(questionAndAnswers));
        return questionAndAnswers;
    }


    public static void saveResponses(String[] playerNames, Map<String, int[]> player1Stats, Map<String, int[]> player2Stats) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "INSERT INTO study_guide (topic, correct_answer, incorrect_answer, player_id, player_name) VALUES (?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            for (Map.Entry<String, int[]> entry : player1Stats.entrySet()) {
                pstmt.setString(1, entry.getKey());
                pstmt.setInt(2, entry.getValue()[0]); 
                pstmt.setInt(3, entry.getValue()[1]); // incorrect answers
                pstmt.setInt(4, 1);
                pstmt.setString(5, playerNames[0]);
                
                pstmt.executeUpdate();
            }
            for (Map.Entry<String, int[]> entry : player2Stats.entrySet()) {
                pstmt.setString(1, entry.getKey());
                pstmt.setInt(2, entry.getValue()[0]); // correct answers
                pstmt.setInt(3, entry.getValue()[1]); // incorrect answers
                pstmt.setInt(4, 2);
                pstmt.setString(5, playerNames[1]);

                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static String getWorstTopic(String playerName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String topic = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT topic, incorrect_answer FROM study_guide WHERE player_name = ? ORDER BY incorrect_answer DESC";
           
            pstmt = conn.prepareStatement(sql);

             // First, get the specific question and its correct answer
             pstmt.setString(1, playerName);
             try (ResultSet rs = pstmt.executeQuery()) {
                 if (rs.next()) {
                     topic = rs.getString("topic");
                 }
             }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return topic;
        }
       
    }

