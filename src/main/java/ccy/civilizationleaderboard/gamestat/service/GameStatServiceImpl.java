package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.mapper.GameStatRequestMapper;
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
    public GameStatResponse editGameStat(GameStatRequest editRequest) {

        GameStat gameStat = gameStatRequestMapper.apply(editRequest);
        GameStat editedGameStat = gameStatRepository.save(gameStat);

        return gameStatResponseMapper.apply(editedGameStat);
    }


    @Override
    public void deleteGameStatBy(int id) {
        gameStatRepository.deleteById(id);
    }



    @Override
    public boolean doesExist(GameStatRequest postRequest) {
        return gameStatRepository.existsByPlacementAndVictoryPointsAndMilitaryPointsAndSciencePointsAndCulturePointsAndGoldAndReligiousPointsAndDiplomaticPoints(
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