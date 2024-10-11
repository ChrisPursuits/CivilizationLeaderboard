package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatRepository extends JpaRepository<GameStat, Integer> {
}
