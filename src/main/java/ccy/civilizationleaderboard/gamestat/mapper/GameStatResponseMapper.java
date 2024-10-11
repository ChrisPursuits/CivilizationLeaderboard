package ccy.civilizationleaderboard.gamestat.mapper;

import ccy.civilizationleaderboard.gamestat.GameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameStatResponseMapper implements Function<GameStat, GameStatResponse> {


    @Override
    public GameStatResponse apply(GameStat gameStat) {
        return new GameStatResponse(
                gameStat.getId(),
                gameStat.getPlacement(),
                gameStat.getVictoryPoints(),
                gameStat.getMilitaryPoints(),
                gameStat.getSciencePoints(),
                gameStat.getCulturePoints(),
                gameStat.getGold(),
                gameStat.getReligiousPoints(),
                gameStat.getDiplomaticPoints()
        );
    }
}
