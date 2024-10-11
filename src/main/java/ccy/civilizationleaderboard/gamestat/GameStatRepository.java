package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GameStatRepository extends JpaRepository<GameStat, Integer> {

    Set<GameStat> findGameStatsByGameId(int gameId);
}
