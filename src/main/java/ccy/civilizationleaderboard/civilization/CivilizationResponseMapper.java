package ccy.civilizationleaderboard.civilization;

import ccy.civilizationleaderboard.civilization.model.Civilization;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CivilizationResponseMapper implements Function<Civilization, CivilizationResponse> {

    @Override
    public CivilizationResponse apply(Civilization civilization) {
        return new CivilizationResponse(
                civilization.getLeader()
        );
    }
}
