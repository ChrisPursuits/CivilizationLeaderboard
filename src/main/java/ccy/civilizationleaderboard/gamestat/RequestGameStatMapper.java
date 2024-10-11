package ccy.civilizationleaderboard.gamestat;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestGameStatMapper implements Function<GameStat, RequestGameStat> {


    @Override
    public RequestGameStat apply(GameStat gameStat) {
        return new RequestGameStat(
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
