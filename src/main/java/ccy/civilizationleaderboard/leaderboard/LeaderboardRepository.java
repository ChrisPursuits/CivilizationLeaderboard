package ccy.civilizationleaderboard.leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Integer> {

    boolean existsByNameAndDescription(String name, String description);
}
