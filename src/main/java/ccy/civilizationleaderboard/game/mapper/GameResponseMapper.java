package ccy.civilizationleaderboard.game.mapper;

import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.dto.GameResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GameResponseMapper implements Function<Game, GameResponse> {
    @Override
    public GameResponse apply(Game game) {
        return new GameResponse(
                game.getId(),
                game.getTitle(),
                game.getDescription()
        );
    }
}
