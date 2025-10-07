package com.game.mlb_game.service;

import com.game.mlb_game.model.User;
import com.game.mlb_game.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User registerUser(String username, String password) {
        // TODO: hash password before saving
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTotalQuestions(0);
        user.setCorrectAnswers(0);
        return repo.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public User updateStats(String username, boolean correct) {
        User user = repo.findByUsername(username).orElseThrow();
        user.setTotalQuestions(user.getTotalQuestions() + 1);
        if (correct) {
            user.setCorrectAnswers(user.getCorrectAnswers() + 1);
        }
        return repo.save(user);
    }
}
