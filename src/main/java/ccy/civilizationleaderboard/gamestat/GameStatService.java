package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.dto.CreateGameStat;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import ccy.civilizationleaderboard.gamestat.dto.RequestGameStat;

public interface GameStatService {
    RequestGameStat getGameStatById(int id );
    RequestGameStat createGameStat(CreateGameStat gameStat);
    RequestGameStat editGameStat(EditGameStat gameStat);
    void deleteGameStatById(int id);

}
