package ccy.civilizationleaderboard.leaderboard.service;

import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.LeaderboardRepository;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;
import ccy.civilizationleaderboard.leaderboard.mapper.LeaderboardRequestMapper;
import ccy.civilizationleaderboard.leaderboard.mapper.LeaderboardResponseMapper;
import ccy.civilizationleaderboard.user.UserRepository;
import ccy.civilizationleaderboard.user.UserResponse;
import ccy.civilizationleaderboard.user.UserResponseMapper;
import ccy.civilizationleaderboard.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final LeaderboardRequestMapper leaderboardRequestMapper;
    private final LeaderboardResponseMapper leaderboardResponseMapper;
     private final UserRepository userRepository;


    @Override
    public LeaderboardResponse getLeaderboardBy(int id) {
        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(id);

        if (leaderboardOptional.isEmpty()) {
            return null;
        }

        Leaderboard foundLeaderboard = leaderboardOptional.get();
        return leaderboardResponseMapper.apply(foundLeaderboard);
    }

    @Override
    public LeaderboardResponse createLeaderboard(LeaderboardRequest postRequest) {

        Leaderboard leaderboard = leaderboardRequestMapper.apply(postRequest);
        Leaderboard createdLeaderboard = leaderboardRepository.save(leaderboard);

        return leaderboardResponseMapper.apply(createdLeaderboard);
    }

    @Override
    public LeaderboardResponse editLeaderboard(int id, LeaderboardRequest putRequest) {

        Leaderboard leaderboard = leaderboardRequestMapper.apply(putRequest);
        leaderboard.setId(id);

        Leaderboard updatedLeaderboard = leaderboardRepository.save(leaderboard);

        return leaderboardResponseMapper.apply(updatedLeaderboard);
    }

    @Override
    public void deleteLeaderboard(int id) {
        leaderboardRepository.deleteById(id);
    }





    @Override
    public boolean doesExist(int id) {
        return leaderboardRepository.existsById(id);
    }

    @Override
    public boolean doesExist(LeaderboardRequest postRequest) {
        return leaderboardRepository.existsByNameAndDescription(
                postRequest.name(),
                postRequest.description()
        );
    }

    @Override
    public LeaderboardResponse addUserToLeaderboard(int leaderboardId, int userId) {

        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(leaderboardId);
        if (leaderboardOptional.isEmpty()) {
            return null;
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();

        Leaderboard leaderboard = leaderboardOptional.get();
        leaderboard.getLeaderboardMembers().add(user);

        Leaderboard updatedLeaderboard = leaderboardRepository.save(leaderboard);
        return leaderboardResponseMapper.apply(updatedLeaderboard);
    }
}
