package ccy.civilizationleaderboard.game.mapper;

import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.dto.GameRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameRequestMapper implements Function<GameRequest, Game> {


    @Override
    public Game apply(GameRequest gameRequest) {
        return new Game(
                gameRequest.title(),
                gameRequest.description()
        );
    }
}
