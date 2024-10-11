package ccy.civilizationleaderboard.civilization;

import java.util.function.Function;

public class RequestCivilizationMapper implements Function<Civilization, RequestCivilization> {
    @Override
    public RequestCivilization apply(Civilization civilization) {
        return new RequestCivilization(
                civilization.getName()
        );
    }
}
