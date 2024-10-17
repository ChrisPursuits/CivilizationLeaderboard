package ccy.civilizationleaderboard.repository;

import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.LeaderboardRepository;
import ccy.civilizationleaderboard.user.repository.UserRepository;
import ccy.civilizationleaderboard.user.model.Role;
import ccy.civilizationleaderboard.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ActiveProfiles("test")
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Test
    void UserRepository_findByUsername_ReturnsUser() {
        User user = User.builder()
                .id(1)
                .username("Chris")
                .password("{encoded_password_for_Chris}")
                .role(Role.ROLE_USER)
                .build();

        User expectedUser = userRepository.save(user);

        User actualUser = userRepository.findByUsername("Chris").get();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void UserRepository_findAllByLeaderboardId_ReturnsListOfLeaderboardMembers() {

        Leaderboard leaderboard = new Leaderboard(1, "Leaderboard", "description", new ArrayList<>());
        leaderboardRepository.save(leaderboard);

        User chris = User.builder()
                .id(1)
                .username("Chris")
                .password("{encoded_password_for_Chris}")
                .role(Role.ROLE_USER)
                .build();

        User markus = User.builder()
                .id(2)
                .username("Markus")
                .password("{encoded_password_for_Markus}")
                .role(Role.ROLE_USER)
                .build();

        User engjëll = User.builder()
                .id(3)
                .username("Engjëll")
                .password("{encoded_password_for_Engjëll}")
                .role(Role.ROLE_USER)
                .build();

        User mikkel = User.builder()
                .id(4)
                .username("Mikkel")
                .password("{encoded_password_for_Mikkel}")
                .role(Role.ROLE_USER)
                .build();


        chris.setLeaderboardList(List.of(leaderboardRepository.findById(1).get())); markus.setLeaderboardList(List.of(leaderboardRepository.findById(1).get())); engjëll.setLeaderboardList(List.of(leaderboardRepository.findById(1).get())); mikkel.setLeaderboardList(List.of(leaderboardRepository.findById(1).get()));
        userRepository.save(chris); userRepository.save(markus); userRepository.save(engjëll); userRepository.save(mikkel);

        List<User> users = new ArrayList<>(List.of(userRepository.findByUsername("Chris").get(), userRepository.findByUsername("Markus").get(), userRepository.findByUsername("Engjëll").get(), userRepository.findByUsername("Mikkel").get()));
        leaderboard.setLeaderboardMembers(users);
        leaderboardRepository.save(leaderboard);

        List<User> expectedUsers = new ArrayList<>(List.of(userRepository.findByUsername("Chris").get(), userRepository.findByUsername("Markus").get(), userRepository.findByUsername("Engjëll").get(), userRepository.findByUsername("Mikkel").get()));

        int leaderboardId = 1;
        List<User> actualUsers = userRepository.findAllByLeaderboardId(leaderboardId);

        assertEquals(expectedUsers, actualUsers);
    }
}