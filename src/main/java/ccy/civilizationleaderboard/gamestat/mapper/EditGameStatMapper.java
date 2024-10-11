package ccy.civilizationleaderboard.gamestat.mapper;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EditGameStatMapper implements Function<EditGameStat, GameStat> {

    @Override
    public GameStat apply(EditGameStat editGameStat) {
        return new GameStat(
                editGameStat.placement(),
                editGameStat.victoryPoints(),
                editGameStat.militaryPoints(),
                editGameStat.sciencePoints(),
                editGameStat.culturePoints(),
                editGameStat.gold(),
                editGameStat.religiousPoints(),
                editGameStat.diplomaticPoints()
        );
    }
}
