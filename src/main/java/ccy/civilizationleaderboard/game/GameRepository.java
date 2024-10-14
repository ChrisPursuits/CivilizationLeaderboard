package ccy.civilizationleaderboard.game;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GameRepository extends JpaRepository<Game, Integer> {

    boolean existsByTitleAndDescription(String title, String description);
}
