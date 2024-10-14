package ccy.civilizationleaderboard.game.service;

import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.GameRepository;
import ccy.civilizationleaderboard.game.dto.GameRequest;
import ccy.civilizationleaderboard.game.dto.GameResponse;
import ccy.civilizationleaderboard.game.mapper.GameRequestMapper;
import ccy.civilizationleaderboard.game.mapper.GameResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameRequestMapper GameRequestMapper;
    private final GameResponseMapper GameResponseMapper;
    private final GameResponseMapper gameResponseMapper;


    @Override
    public GameResponse getGameBy(int id) {

        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isEmpty()) {
            return null;
        }

        Game gameResponse = gameOptional.get();
        return gameResponseMapper.apply(gameResponse);
    }

    @Override
    public GameResponse createGame(GameRequest createRequest) {
        return null;
    }

    @Override
    public GameResponse editGame(GameRequest editRequest) {
        return null;
    }

    @Override
    public void deleteGameBy(int id) {

    }

    @Override
    public boolean doesExist(int id) {
        return gameRepository.existsById(id);
    }

    @Override
    public boolean doesExist(GameRequest postRequest) {
        return gameRepository.existsByTitleAndDescription(
                postRequest.title(),
                postRequest.description()
        );
    }
}
