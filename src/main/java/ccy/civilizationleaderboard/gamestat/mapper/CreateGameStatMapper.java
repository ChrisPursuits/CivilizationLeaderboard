package ccy.civilizationleaderboard.gamestat.mapper;

import ccy.civilizationleaderboard.gamestat.GameStat;
import ccy.civilizationleaderboard.gamestat.dto.CreateGameStat;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateGameStatMapper implements Function<CreateGameStat, GameStat> {


    @Override
    public GameStat apply(CreateGameStat createGameStat) {
        return new GameStat(
                createGameStat.placement(),
                createGameStat.victoryPoints(),
                createGameStat.militaryPoints(),
                createGameStat.sciencePoints(),
                createGameStat.culturePoints(),
                createGameStat.gold(),
                createGameStat.religiousPoints(),
                createGameStat.diplomaticPoints()
        );
    }
}
