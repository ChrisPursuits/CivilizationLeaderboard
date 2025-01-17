package ccy.civilizationleaderboard.repository;

import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DataJpaTest
class LeaderboardRepositoryTest {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Test
    void LeaderboardRepository_existsByNameAndDescription_ReturnTrue() {
        Leaderboard leaderboard = new Leaderboard("First Leaderboard", "This is a test leaderboard");

        leaderboardRepository.save(leaderboard);

        assertTrue(leaderboardRepository.existsByNameAndDescription(leaderboard.getName(), leaderboard.getDescription()));
    }

    @Test
    void LeaderboardRepository_existsByNameAndDescription_ReturnFalse() {
        Leaderboard leaderboard = new Leaderboard("This does not exist", "Neither does this");

        assertFalse(leaderboardRepository.existsByNameAndDescription(leaderboard.getName(), leaderboard.getDescription()));
    }
}