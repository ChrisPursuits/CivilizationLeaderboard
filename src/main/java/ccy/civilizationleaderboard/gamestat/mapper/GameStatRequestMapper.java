package ccy.civilizationleaderboard.gamestat.mapper;

import ccy.civilizationleaderboard.civilization.CivilizationRepository;
import ccy.civilizationleaderboard.civilization.model.Civilization;
import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.GameRepository;
import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.user.repository.UserRepository;
import ccy.civilizationleaderboard.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GameStatRequestMapper implements Function<GameStatRequest, GameStat> {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final CivilizationRepository civilizationRepository;

    @Override
    public GameStat apply(GameStatRequest gameStatRequest) {

        User user = userRepository.findByUsername(gameStatRequest.username()).orElse(null);
        Game game = gameRepository.findById(gameStatRequest.gameId()).orElse(null);
        Civilization civilization = civilizationRepository.findById(gameStatRequest.civilizationId()).orElse(null);

        return new GameStat(
                game,
                user,
                civilization,
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
