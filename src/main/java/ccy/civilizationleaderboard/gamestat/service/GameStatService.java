package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;

import java.util.Set;

public interface GameStatService {
    //Crud
    GameStatResponse getGameStatBy(int id );
    Set<GameStatResponse> getAllGameStatsByGameId(int gameId);
    GameStatResponse createGameStat(GameStatRequest postRequest);
    GameStatResponse editGameStat(int id, GameStatRequest putRequest);
    void deleteGameStatBy(int id);

    //other
    boolean doesExist(GameStatRequest postRequest);
    boolean doesExist(int id);
}
