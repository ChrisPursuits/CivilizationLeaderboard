package ccy.civilizationleaderboard.leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Integer> {

    boolean existsByNameAndDescription(String name, String description);

    @Query("SELECT l FROM Leaderboard l JOIN l.leaderboardMembers u WHERE u.username = :username")
    List<Leaderboard> findAllByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Leaderboard l JOIN l.leaderboardMembers u " +
            "WHERE l.id = :leaderboardId AND u.username = :username")
    boolean isUserInLeaderboard(int leaderboardId, String username);
}

