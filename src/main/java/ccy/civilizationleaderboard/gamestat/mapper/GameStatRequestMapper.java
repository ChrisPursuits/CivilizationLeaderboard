package ccy.civilizationleaderboard.gamestat.mapper;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameStatRequestMapper implements Function<GameStatRequest, GameStat> {


    @Override
    public GameStat apply(GameStatRequest gameStatRequest) {
        return new GameStat(
                gameStatRequest.placement(),
                gameStatRequest.victoryPoints(),
                gameStatRequest.militaryPoints(),
                gameStatRequest.sciencePoints(),
                gameStatRequest.culturePoints(),
                gameStatRequest.gold(),
                gameStatRequest.religiousPoints(),
                gameStatRequest.diplomaticPoints()
        );
    }
}
