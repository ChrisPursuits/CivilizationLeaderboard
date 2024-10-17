package ccy.civilizationleaderboard.user.repository;

import ccy.civilizationleaderboard.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.leaderboardList l WHERE l.id = :leaderboardId")
    List<User> findAllByLeaderboardId(int leaderboardId);

    @Query("SELECT u FROM User u JOIN u.gameList g WHERE g.id = :gameId")
    List<User> findAllByGameId(int gameId);

    boolean existsByUsername(String username);
}