package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.dto.CreateGameStat;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;

import java.util.Set;

public interface GameStatService {
    //Crud
    GameStatResponse getGameStatById(int id );
    Set<GameStatResponse> getAllGameStatsByGameId(int gameId);
    GameStatResponse createGameStat(CreateGameStat gameStat);
    GameStatResponse editGameStat(EditGameStat gameStat);
    void deleteGameStatById(int id);

    //other
    boolean doesGameStatExist(CreateGameStat gameStat);
    boolean doesGameStatExist(EditGameStat gameStat);
}
