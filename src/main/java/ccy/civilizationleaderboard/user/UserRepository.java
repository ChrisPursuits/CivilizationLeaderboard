package ccy.civilizationleaderboard.user;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.leaderboardList l WHERE l.id = :leaderboardId " +
            "ORDER BY " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 1) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 2) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 3) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 4) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 5) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 6) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 7) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 8) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 9) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 10) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 11) DESC, " +
            "(SELECT COUNT(ph) FROM u.placementHistory ph WHERE ph = 12) DESC")
    List<User> findAllUsersByLeaderboardIdSortedByPlacements(int leaderboardId);


    @Query("SELECT gs FROM GameStat gs " +
            "JOIN gs.game g " +
            "JOIN g.userList u " +
            "WHERE u.username = :username")
    List<GameStat> findAllGameStatsByUsername(String username);

}
