package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatRequestMapper;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatResponseMapper;
import ccy.civilizationleaderboard.user.UserRepository;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameStatServiceImpl implements GameStatService {

    private final GameStatResponseMapper gameStatResponseMapper;
    private final GameStatRequestMapper gameStatRequestMapper;
    private final GameStatRepository gameStatRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    @Override
    public GameStatResponse getGameStatBy(int id) {

        Optional<GameStat> gameStatOptional = gameStatRepository.findById(id);
        if (gameStatOptional.isEmpty()) {
            return null;
        }

        GameStat gameStat = gameStatOptional.get();
        return gameStatResponseMapper.apply(gameStat);
    }


    @Override
    public Set<GameStatResponse> getAllGameStatsByGameId(int gameId) {
        Set<GameStat> gameStatSet = gameStatRepository.findGameStatsByGameId(gameId);

        return gameStatSet.stream()
                .map(gameStatResponseMapper)
                .collect(Collectors.toSet());
    }


    @Override
    public GameStatResponse createGameStat(GameStatRequest postRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(postRequest);
        GameStat savedGameStat = gameStatRepository.save(gameStat);

        userService.updateUserPlacementHistory(gameStat);

        return gameStatResponseMapper.apply(savedGameStat);
    }


    @Override
    public GameStatResponse editGameStat(int id, GameStatRequest putRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(putRequest);
        gameStat.setId(id);

        GameStat editedGameStat = gameStatRepository.save(gameStat);

        return gameStatResponseMapper.apply(editedGameStat);
    }


    @Override
    public void deleteGameStatBy(int id) {
        gameStatRepository.deleteById(id);
    }


    //TODO consider only checking for 4 parameters as the chances for these 4 to be exactly the same should be relativly low over thounds of games?
    @Override
    public boolean doesExist(GameStatRequest postRequest) {

        Optional<User> userOptional = userRepository.findById(postRequest.user().getId());
        if (userOptional.isEmpty()) {
            return true; //This will cause controller to respond with BadRequest.
        }

        User user = userOptional.get();

        return gameStatRepository.existsByUserAndSelectedCivilizationAndPlacementAndVictoryPointsAndMilitaryPointsAndSciencePointsAndCulturePointsAndGoldAndReligiousPointsAndDiplomaticPoints(
                user,
                postRequest.selectedCivilization(),
                postRequest.placement(),
                postRequest.victoryPoints(),
                postRequest.militaryPoints(),
                postRequest.sciencePoints(),
                postRequest.culturePoints(),
                postRequest.gold(),
                postRequest.religiousPoints(),
                postRequest.diplomaticPoints()
        );
    }

    @Override
    public boolean doesExist(int id) {
        return gameStatRepository.existsById(id);
    }
}