package com.game.mlb_game.dto;

import com.game.mlb_game.model.Pitcher;
import java.util.List;

public class AnswerRequest {
    private Long userId;
    private Long chosenPitcherId;
    private List<Pitcher> pitchers;
    private String stat;

    // --- Getters ---
    public Long getUserId() {
        return userId;
    }

    public Long getChosenPitcherId() {
        return chosenPitcherId;
    }

    public List<Pitcher> getPitchers() {
        return pitchers;
    }

    public String getStat() {
        return stat;
    }

    // --- Setters ---
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setChosenPitcherId(Long chosenPitcherId) {
        this.chosenPitcherId = chosenPitcherId;
    }

    public void setPitchers(List<Pitcher> pitchers) {
        this.pitchers = pitchers;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
