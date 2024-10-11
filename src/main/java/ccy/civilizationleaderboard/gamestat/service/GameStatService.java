package ccy.civilizationleaderboard.gamestat.service;

import ccy.civilizationleaderboard.gamestat.dto.CreateGameStat;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;

public interface GameStatService {
    GameStatResponse getGameStatById(int id );
    GameStatResponse createGameStat(CreateGameStat gameStat);
    GameStatResponse editGameStat(EditGameStat gameStat);
    void deleteGameStatById(int id);

}
