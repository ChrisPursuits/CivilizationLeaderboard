package ccy.civilizationleaderboard.civilization;

import ccy.civilizationleaderboard.civilization.model.Civilization;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestCivilizationMapper implements Function<Civilization, RequestCivilization> {

    @Override
    public RequestCivilization apply(Civilization civilization) {
        return new RequestCivilization(
                civilization.getName()
        );
    }
}
