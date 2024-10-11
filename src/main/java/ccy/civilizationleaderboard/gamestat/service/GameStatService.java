package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.dto.CreateGameStat;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;

import java.util.Set;

public interface GameStatService {
    //Crud
    GameStatResponse getGameStatBy(int id );
    Set<GameStatResponse> getAllGameStatsByGameId(int gameId);
    GameStatResponse createGameStat(CreateGameStat gameStat);
    GameStatResponse editGameStat(EditGameStat gameStat);
    void deleteGameStatBy(int id);

    //other
    boolean doesGameStatExist(CreateGameStat gameStat);
    boolean doesGameStatExist(EditGameStat gameStat);
    boolean doesGameStatExist(int id);
}
