package ccy.civilizationleaderboard.gamestat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatRepository extends JpaRepository<GameStat, Integer> {
}
