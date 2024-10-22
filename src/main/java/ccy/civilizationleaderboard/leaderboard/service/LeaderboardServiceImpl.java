package ccy.civilizationleaderboard.leaderboard.service;

import ccy.civilizationleaderboard.exception.LeaderboardNotFoundException;
import ccy.civilizationleaderboard.invite.model.Invite;
import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.LeaderboardRepository;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;
import ccy.civilizationleaderboard.leaderboard.mapper.LeaderboardRequestMapper;
import ccy.civilizationleaderboard.leaderboard.mapper.LeaderboardResponseMapper;
import ccy.civilizationleaderboard.user.repository.UserRepository;
import ccy.civilizationleaderboard.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public List<LeaderboardResponse> getAllLeaderboardsByUsername(String username) {
        List<Leaderboard> leaderboardList = leaderboardRepository.findAllByUsername(username);
        return leaderboardList.stream()
                .map(leaderboardResponseMapper)
                .toList();
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
    public LeaderboardResponse addUserToLeaderboard(int leaderboardId, String username) {

        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(leaderboardId);
        if (leaderboardOptional.isEmpty()) {
            return null;
        }

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        Leaderboard leaderboard = leaderboardOptional.get();
        leaderboard.getLeaderboardMembers().add(user);

        Leaderboard updatedLeaderboard = leaderboardRepository.save(leaderboard);
        return leaderboardResponseMapper.apply(updatedLeaderboard);
    }

    //add this point im actually not sure if im going overboard with all these null checks, might be redundant,
    //since there could be other parts of the code that already checks for the existence, so im just doing it again for no reason.
    //consider this for future development / consider which layer so handle all null checks f.eks. the ServiceLayer is probably might be ideal.
    @Override
    public void acceptedInvitation(Invite invite) {

        Leaderboard leaderboard = invite.getLeaderboard();
        User user = invite.getReceiver();

        boolean doesLeaderboardExist = doesExist(leaderboard.getId());
        if (!doesLeaderboardExist) {
            throw new LeaderboardNotFoundException("The leaderboard does not exist.");
        }

        boolean doesUserExist = userRepository.existsById(user.getId());
        if (!doesUserExist) {
            throw new UsernameNotFoundException("User doesnt exist"); // should probably use costume exception making the error more precise.
        }

        leaderboard.getLeaderboardMembers().add(user);
        leaderboardRepository.save(leaderboard);
    }
}
