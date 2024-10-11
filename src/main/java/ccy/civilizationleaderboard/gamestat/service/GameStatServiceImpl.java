package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatRequestMapper;
import ccy.civilizationleaderboard.gamestat.mapper.EditGameStatMapper;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatResponseMapper;
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
    private final EditGameStatMapper editGameStatMapper;
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


    @Override
    public GameStatResponse createGameStat(GameStatRequest createRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(createRequest);
        GameStat savedGameStat = gameStatRepository.save(gameStat);

        return gameStatResponseMapper.apply(savedGameStat);
    }


    @Override
    public GameStatResponse editGameStat(EditGameStat editRequest) {

        GameStat gameStat = editGameStatMapper.apply(editRequest);
        GameStat editedGameStat = gameStatRepository.save(gameStat);

        return gameStatResponseMapper.apply(editedGameStat);
    }


    @Override
    public void deleteGameStatBy(int id) {
        gameStatRepository.deleteById(id);
    }


    @Override
    public boolean doesGameStatExist(GameStatRequest gameStat) {
        return gameStatRepository.existsByPlacementAndVictoryPointsAndMilitaryPointsAndSciencePointsAndCulturePointsAndGoldAndReligiousPointsAndDiplomaticPoints(
                gameStat.placement(),
                gameStat.victoryPoints(),
                gameStat.militaryPoints(),
                gameStat.sciencePoints(),
                gameStat.culturePoints(),
                gameStat.gold(),
                gameStat.religiousPoints(),
                gameStat.diplomaticPoints()
        );
    }

    @Override
    public boolean doesGameStatExist(EditGameStat gameStat) {
        return gameStatRepository.existsByPlacementAndVictoryPointsAndMilitaryPointsAndSciencePointsAndCulturePointsAndGoldAndReligiousPointsAndDiplomaticPoints(
                gameStat.placement(),
                gameStat.victoryPoints(),
                gameStat.militaryPoints(),
                gameStat.sciencePoints(),
                gameStat.culturePoints(),
                gameStat.gold(),
                gameStat.religiousPoints(),
                gameStat.diplomaticPoints()
        );
    }

    @Override
    public boolean doesGameStatExist(int id) {
        return gameStatRepository.existsById(id);
    }
}