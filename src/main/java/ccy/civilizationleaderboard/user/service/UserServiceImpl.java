package ccy.civilizationleaderboard.user.service;

import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.UserResponse;
import ccy.civilizationleaderboard.user.UserResponseMapper;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final GameStatRepository gameStatRepository;
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;


    @Override
    public User findUserBy(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return null;
        }

        User foundUser = userOptional.get();
        return foundUser;
    }

    @Override
    public void updateUserPlacementHistory(GameStat gameStat) {
        User user = gameStat.getUser();
        List<GameStat> allUserGameStats = gameStatRepository.findAllByUser(user);

        List<Integer> placementHistory = calculatePlacementHistory(allUserGameStats);
        user.setPlacementHistory(placementHistory);

        userRepository.save(user);
    }


    @Override
    public List<UserResponse> getAllUsersByLeaderboardIdSorted(int leaderboardId) {
        List<User> sortedByPlacements = userRepository.findAllUsersByLeaderboardIdSortedByPlacements(leaderboardId);

        for (User user : sortedByPlacements) {

            //gets all the gameStat on user, and sets total games played.
            List<GameStat> allUserGameStats = gameStatRepository.findAllByUser(user);
            int totalGamesPlayed = allUserGameStats.size();
            user.setTotalGamesPlayed(totalGamesPlayed);

            //calculate placementHistory
            List<Integer> placementHistory = calculatePlacementHistory(allUserGameStats);
            user.setPlacementHistory(placementHistory);
        }

        //map user list to userResponse list.
        List<UserResponse> userResponseList = sortedByPlacements
                .stream()
                .map(userResponseMapper)
                .toList();

        return userResponseList;
    }


    private List<Integer> calculatePlacementHistory(List<GameStat> allUserGameStats) {
        List<Integer> placementHistory = new ArrayList<>(12); //max 12 placements

        int firstPlaceCount = 0;
        int secondPlaceCount = 0;
        int thirdPlaceCount = 0;
        int fourthPlaceCount = 0;
        int fifthPlaceCount = 0;
        int sixthPlaceCount = 0;
        int seventhPlaceCount = 0;
        int eighthPlaceCount = 0;
        int ninthPlaceCount = 0;
        int tenthPlaceCount = 0;
        int eleventhPlaceCount = 0;
        int twelfthPlaceCount = 0;

        placementHistory.addFirst(firstPlaceCount);
        placementHistory.add(1, secondPlaceCount);
        placementHistory.add(2, thirdPlaceCount);
        placementHistory.add(3, fourthPlaceCount);
        placementHistory.add(4, fifthPlaceCount);
        placementHistory.add(5, sixthPlaceCount);
        placementHistory.add(6, seventhPlaceCount);
        placementHistory.add(7, eighthPlaceCount);
        placementHistory.add(8, ninthPlaceCount);
        placementHistory.add(9, tenthPlaceCount);
        placementHistory.add(10, eleventhPlaceCount);
        placementHistory.addLast(twelfthPlaceCount);

        for (GameStat stat : allUserGameStats) {

            switch (stat.getPlacement()) {
                case FIRST -> {
                    firstPlaceCount++;
                    placementHistory.addFirst(firstPlaceCount);
                }
                case SECOND -> {
                    secondPlaceCount++;
                    placementHistory.add(1, secondPlaceCount);
                }
                case THIRD -> {
                    firstPlaceCount++;
                    placementHistory.add(2, thirdPlaceCount);
                }
                case FOURTH -> {
                    fourthPlaceCount++;
                    placementHistory.add(3, fourthPlaceCount);
                }
                case FIFTH -> {
                    fifthPlaceCount++;
                    placementHistory.add(4, fifthPlaceCount);
                }
                case SIXTH -> {
                    sixthPlaceCount++;
                    placementHistory.add(5, sixthPlaceCount);
                }
                case SEVENTH -> {
                    seventhPlaceCount++;
                    placementHistory.add(6, seventhPlaceCount);
                }
                case EIGHTH -> {
                    eighthPlaceCount++;
                    placementHistory.add(7, eighthPlaceCount);
                }
                case NINTH -> {
                    ninthPlaceCount++;
                    placementHistory.add(8, ninthPlaceCount);
                }
                case TENTH -> {
                    tenthPlaceCount++;
                    placementHistory.add(9, tenthPlaceCount);
                }
                case ELEVENTH -> {
                    eleventhPlaceCount++;
                    placementHistory.add(10, eleventhPlaceCount);
                }
                case TWELFTH -> {
                    twelfthPlaceCount++;
                    placementHistory.addLast(twelfthPlaceCount);
                }
            }
        }
        return placementHistory;
    }
}
