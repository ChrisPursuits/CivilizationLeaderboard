package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatRequestMapper;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatResponseMapper;
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

    private final UserService userService;

    private final GameStatResponseMapper gameStatResponseMapper;
    private final GameStatRequestMapper gameStatRequestMapper;
    private final GameStatRepository gameStatRepository;



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

    //TODO
    //make null checks for Game, civilization and user. If any of these are null, throw an exception.
    //this should be caught by the controller, and return a BadRequest to the front end.
    @Override
    public GameStatResponse createGameStat(GameStatRequest postRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(postRequest); //the mapper creates the relation between gamestat and game.
        GameStat savedGameStat = gameStatRepository.save(gameStat);

        userService.updateUserPlacementHistory(savedGameStat);
        userService.updateGameHistory(savedGameStat);

        return gameStatResponseMapper.apply(savedGameStat);
    }


    @Override
    public GameStatResponse editGameStat(int gameStatId, GameStatRequest putRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(putRequest);
        gameStat.setId(gameStatId);

        GameStat editedGameStat = gameStatRepository.save(gameStat);

        userService.updateUserPlacementHistory(editedGameStat);

        return gameStatResponseMapper.apply(editedGameStat);
    }


    @Override
    public void deleteGameStatBy(int id) {
        GameStat gameStat = gameStatRepository.findById(id).orElse(null);
        gameStatRepository.deleteById(id);

        User user = gameStat.getUser();
        user.getGameStatList().remove(gameStat);

        userService.updateUserPlacementHistory(user);
    }


    //TODO consider only checking for 4 parameters as the chances for these 4 to be exactly the same should be relativly low over thounds of games?
    @Override
    public boolean doesExist(GameStatRequest postRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(postRequest);

        return gameStatRepository.existsByGameAndUserAndSelectedCivilizationAndPlacementAndVictoryPointsAndMilitaryPointsAndSciencePointsAndCulturePointsAndGoldAndReligiousPointsAndDiplomaticPoints(
                gameStat.getGame(),
                gameStat.getUser(),
                gameStat.getSelectedCivilization(),
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