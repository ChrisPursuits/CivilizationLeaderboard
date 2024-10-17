package ccy.civilizationleaderboard.game;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface GameRepository extends JpaRepository<Game, Integer> {

    boolean existsByTitleAndDescription(String title, String description);

    @Query("SELECT g FROM Game g CROSS JOIN User u WHERE u.username = :username")
    List<Game> findAllGamesByUsername(String username);
}
