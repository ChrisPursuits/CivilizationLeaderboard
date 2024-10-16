package ccy.civilizationleaderboard.repository;

import ccy.civilizationleaderboard.user.UserRepository;
import ccy.civilizationleaderboard.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
    }

    @Test
    void findAllUsersByLeaderboardIdSortedByPlacements() {
    }

    @Test
    void findAllGameStatsByUsername() {
    }
}