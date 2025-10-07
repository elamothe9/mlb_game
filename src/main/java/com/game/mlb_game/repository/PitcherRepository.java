package com.game.mlb_game.repository;

import com.game.mlb_game.model.Pitcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitcherRepository extends JpaRepository<Pitcher, String> {

    // Get 2 random pitchers
    @Query(value = "SELECT * FROM career_pitching ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<Pitcher> findTwoRandomPitchers();
}
