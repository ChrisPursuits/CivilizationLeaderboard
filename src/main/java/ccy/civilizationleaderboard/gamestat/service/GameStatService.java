package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;

import java.util.Set;

public interface GameStatService {
    //Crud
    GameStatResponse getGameStatBy(int id );
    Set<GameStatResponse> getAllGameStatsByGameId(int gameId);
    GameStatResponse createGameStat(GameStatRequest createRequest);
    GameStatResponse editGameStat(GameStatRequest editRequest);
    void deleteGameStatBy(int id);

    //other
    boolean doesGameStatExist(GameStatRequest gameStat);
    boolean doesGameStatExist(int id);
}
