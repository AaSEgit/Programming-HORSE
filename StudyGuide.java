package horse;
import java.util.HashMap;
import java.util.Map;

public class StudyGuide {
    private Map<String, Integer> topicCounterCorrect;
    private Map<String, Integer> topicCounterAttempts;
    private DataManager dataManager;

    public StudyGuide(DataManager dataManager) {
        this.topicCounterCorrect = new HashMap<>();
        this.topicCounterAttempts = new HashMap<>();
        this.dataManager = dataManager;
    }

    public void updateTopicCounters(String topic, boolean correct) {
        topicCounterAttempts.put(topic, topicCounterAttempts.getOrDefault(topic, 0) + 1);
        if (correct) {
            topicCounterCorrect.put(topic, topicCounterCorrect.getOrDefault(topic, 0) + 1);
        }
    }

    public void savePlayerPerformance(String playerName) {
        int totalQuestionsCorrect = topicCounterCorrect.values().stream().mapToInt(Integer::intValue).sum();
        int totalQuestionsIncorrect = topicCounterAttempts.values().stream().mapToInt(Integer::intValue).sum() - totalQuestionsCorrect;
        int totalQuestions = topicCounterAttempts.values().stream().mapToInt(Integer::intValue).sum();
        
        // Calculate best and worst topics based on attempts and correct answers
        String bestTopic = topicCounterCorrect.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No topic found");
                
        String worstTopic = topicCounterAttempts.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No topic found");
        
        String[] bestTopics = new String[]{bestTopic, "N/A", "N/A"}; // Assuming limited topic information
        String[] worstTopics = new String[]{worstTopic, "N/A", "N/A"};

        // Store these details in the database via DataManager
        dataManager.storePlayerStatistics(playerName, totalQuestionsCorrect, totalQuestionsIncorrect, totalQuestions, bestTopics, worstTopics);
        
        // Print the statistics for review
        printPlayerStatistics(playerName, totalQuestionsCorrect, totalQuestionsIncorrect, totalQuestions, bestTopic, worstTopic);
    }

    private void printPlayerStatistics(String playerName, int totalQuestionsCorrect, int totalQuestionsIncorrect, int totalQuestions, String bestTopic, String worstTopic) {
        System.out.println("Player Statistics for: " + playerName);
        System.out.println("Total Questions Correct: " + totalQuestionsCorrect);
        System.out.println("Total Questions Incorrect: " + totalQuestionsIncorrect);
        System.out.println("Total Questions Attempted: " + totalQuestions);
        System.out.println("Best Topic: " + bestTopic);
        System.out.println("Worst Topic: " + worstTopic);
    }
}
