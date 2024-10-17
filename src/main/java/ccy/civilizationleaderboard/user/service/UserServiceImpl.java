package ccy.civilizationleaderboard.user.service;

import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.GameRepository;
import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.dto.UserResponse;
import ccy.civilizationleaderboard.user.mapper.UserResponseMapper;
import ccy.civilizationleaderboard.user.comparator.PlacementHistoryComparator;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    private final GameRepository gameRepository;
    private final GameStatRepository gameStatRepository;



    @Override
    public List<User> findAllByGameId(int gameId) {
        return userRepository.findAllByGameId(gameId);
    }


    @Override
    public List<UserResponse> getAllUsersByLeaderboardIdSorted(int leaderboardId) {
        List<User> leaderboardMembers = userRepository.findAllByLeaderboardId(leaderboardId);


        //TODO
        //remove this block when back end is finiehsed since it will be redundant.
        for (User user : leaderboardMembers) {
            //gets all the gameStat on user, and sets total games played.
            List<GameStat> allUserGameStats = gameStatRepository.findAllByUser(user);
            int totalGamesPlayed = allUserGameStats.size();
            user.setTotalGamesPlayed(totalGamesPlayed);

            //calculate placementHistory
            List<Integer> placementHistory = calculatePlacementHistory(allUserGameStats);
            user.setPlacementHistory(placementHistory);
        }


        //sort the leaderboard list by placement history
        leaderboardMembers.sort(new PlacementHistoryComparator());

        //map user list to userResponse list.
        List<UserResponse> userResponseList = leaderboardMembers
                .stream()
                .map(userResponseMapper)
                .toList();

        return userResponseList;
    }



    @Override
    public void updateGameHistory(GameStat gameStat) {
        User user = userRepository.findById(gameStat.getUser().getId()).get();
        Game game = gameRepository.findById(gameStat.getGame().getId()).get();

        user.getGameList().addLast(game);
        userRepository.save(user);
    }


    //TODO
    //This should probably be refactored and managed by a costume JPQL query that's called
    //from the GameStat Service, instead of making dependency to this service class.
    @Override
    public void updateUserPlacementHistory(GameStat gameStat) {
        User user = gameStat.getUser();
        List<GameStat> allUserGameStats = gameStatRepository.findAllByUser(user);

        List<Integer> placementHistory = calculatePlacementHistory(allUserGameStats);
        user.setPlacementHistory(placementHistory);

        user.setTotalGamesPlayed(allUserGameStats.size());
        //this line is actually redundant when I called the .setPlacementHistory() in the previous line
        //Jpa automatically updated the db with the corresponding data.
        // (just keeping this comment for future me.)
        userRepository.save(user);
    }

    @Override
    public void updateUserPlacementHistory(User user) {
        List<GameStat> allUserGameStats = user.getGameStatList();

        List<Integer> placementHistory = calculatePlacementHistory(allUserGameStats);
        user.setPlacementHistory(placementHistory);

        user.setTotalGamesPlayed(allUserGameStats.size());

        userRepository.save(user);
    }

    @Override
    public void updateUserPlacementHistory(List<User> allUsers) {
        for (User user : allUsers) {
            updateUserPlacementHistory(user);
        }
    }


    @Override
    public void updateUserGameTableOnGameDeletion(List<User> allUsers, Game gameToDelete) {
        for (User user : allUsers) {
            user.getGameList().remove(gameToDelete);
            userRepository.save(user);
        }
    }




    private List<Integer> calculatePlacementHistory(List<GameStat> allUserGameStats) {
        List<Integer> placementHistory = new ArrayList<>(12); //max 12 placements

        int firstPlaceCounter = 0;
        int secondPlaceCounter = 0;
        int thirdPlaceCounter = 0;
        int fourthPlaceCounter = 0;
        int fifthPlaceCounter = 0;
        int sixthPlaceCounter = 0;
        int seventhPlaceCounter = 0;
        int eighthPlaceCounter = 0;
        int ninthPlaceCounter = 0;
        int tenthPlaceCounter = 0;
        int eleventhPlaceCount = 0;
        int twelfthPlaceCount = 0;

        placementHistory.addAll(List.of(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        for (GameStat stat : allUserGameStats) {

            switch (stat.getPlacement()) {
                case FIRST -> {
                    firstPlaceCounter++;
                    placementHistory.set(0, firstPlaceCounter);
                }

                case SECOND -> {
                    secondPlaceCounter++;
                    placementHistory.set(1, secondPlaceCounter);
                }

                case THIRD -> {
                    thirdPlaceCounter++;
                    placementHistory.set(2, thirdPlaceCounter);
                }

                case FOURTH -> {
                    fourthPlaceCounter++;
                    placementHistory.set(3, fourthPlaceCounter);
                }

                case FIFTH -> {
                    fifthPlaceCounter++;
                    placementHistory.set(4, fifthPlaceCounter);
                }

                case SIXTH -> {
                    sixthPlaceCounter++;
                    placementHistory.set(5, sixthPlaceCounter);
                }
                case SEVENTH -> {
                    seventhPlaceCounter++;
                    placementHistory.set(6, seventhPlaceCounter);
                }
                case EIGHTH -> {
                    eighthPlaceCounter++;
                    placementHistory.set(7, eighthPlaceCounter);
                }
                case NINTH -> {
                    ninthPlaceCounter++;
                    placementHistory.set(8, ninthPlaceCounter);
                }
                case TENTH -> {
                    tenthPlaceCounter++;
                    placementHistory.set(9, tenthPlaceCounter);
                }
                case ELEVENTH -> {
                    eleventhPlaceCount++;
                    placementHistory.set(10, eleventhPlaceCount);
                }
                case TWELFTH -> {
                    twelfthPlaceCount++;
                    placementHistory.set(11, twelfthPlaceCount);
                }
            }
        }
        return placementHistory;
    }


    @Override
    public boolean doesExist(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean doesExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
