package com.game.mlb_game.controller;

import com.game.mlb_game.dto.AnswerRequest;
import com.game.mlb_game.model.Pitcher;
import com.game.mlb_game.model.User;
import com.game.mlb_game.service.QuizService;
import com.game.mlb_game.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public Map<String, Object> getQuiz() {
        return quizService.getQuizQuestion();
    }

    @PostMapping("/answer")
    public ResponseEntity<Boolean> submitAnswer(@RequestBody AnswerRequest request) {
        List<Pitcher> pitchers = request.getPitchers();
        boolean result = quizService.checkAnswer(
                request.getUserId(),
                request.getChosenPitcherId(),
                pitchers,
                request.getStat()
        );
        return ResponseEntity.ok(result);
    }

}
