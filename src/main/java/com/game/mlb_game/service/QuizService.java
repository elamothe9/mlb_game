package com.game.mlb_game.service;

import com.game.mlb_game.model.Pitcher;
import com.game.mlb_game.model.User;
import com.game.mlb_game.repository.PitcherRepository;
import com.game.mlb_game.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {
    private final PitcherRepository repository;
    private final UserRepository userRepository;
    private final List<String> stats = Arrays.asList("wins", "losses", "IP", "K", "BB", "ER", "ERA", "HR", "SV");
    private final Random random = new Random();

    public QuizService(PitcherRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    // existing method to get a question
    public Map<String, Object> getQuizQuestion() {
        List<Pitcher> pitchers;
        String randomStat;

        while (true) {
            pitchers = repository.findTwoRandomPitchers();
            randomStat = stats.get(random.nextInt(stats.size()));
            if (validQuestion(pitchers, randomStat)) {
                break;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("stat", randomStat);
        response.put("pitchers", pitchers);
        return response;
    }

    // NEW: check answer and update user stats
    public boolean checkAnswer(Long userId, Long chosenPitcherId, List<Pitcher> pitchers, String stat) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        Pitcher p1 = pitchers.get(0);
        Pitcher p2 = pitchers.get(1);

        double val1 = getStatValue(p1, stat);
        double val2 = getStatValue(p2, stat);

        boolean lowerIsBetter = Arrays.asList("losses", "bb", "er", "era", "hr")
                .contains(stat.toLowerCase());

        Pitcher correct = lowerIsBetter
                ? (val1 < val2 ? p1 : p2)
                : (val1 > val2 ? p1 : p2);

        boolean correctAnswer = Objects.equals(correct.getPlayerID(), chosenPitcherId);

        // update user stats
        user.incrementTotalQuestions();
        if (correctAnswer) {
            user.incrementCorrectAnswers();
        }
        userRepository.save(user);

        return correctAnswer;
    }

    // helper methods
    private boolean validQuestion(List<Pitcher> pitchers, String randomStat) {
        Pitcher p1 = pitchers.get(0);
        Pitcher p2 = pitchers.get(1);
        double val1 = getStatValue(p1, randomStat);
        double val2 = getStatValue(p2, randomStat);

        if (val1 == val2) return false;

        if ((randomStat.equals("SV") || randomStat.equals("wins") || randomStat.equals("losses"))
                && (val1 < 5 || val2 < 5)) {
            return false;
        }

        if (randomStat.equals("IP") && val1 < 500 && val2 > 2000) {
            return false;
        }

        return true;
    }

    private double getStatValue(Pitcher pitcher, String stat) {
        switch (stat.toLowerCase()) {
            case "wins": return pitcher.getWins();
            case "losses": return pitcher.getLosses();
            case "ip": return pitcher.getIP();
            case "k": return pitcher.getK();
            case "bb": return pitcher.getBB();
            case "er": return pitcher.getER();
            case "era": return pitcher.getERA();
            case "hr": return pitcher.getHR();
            case "sv": return pitcher.getSV();
            default: return -1;
        }
    }
}
