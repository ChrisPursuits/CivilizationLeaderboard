package ccy.civilizationleaderboard.user;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User> findAllByLeaderboardId(int leaderboardId);

    @Query("SELECT gs FROM GameStat gs " +
            "JOIN gs.game g " +
            "JOIN g.user u " +
            "WHERE u.username = :username")
    List<GameStat> findAllGameStatsByUsername(String username);

}
