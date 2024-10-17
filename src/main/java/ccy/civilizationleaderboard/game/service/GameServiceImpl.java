package ccy.civilizationleaderboard.game.service;

import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.GameRepository;
import ccy.civilizationleaderboard.game.dto.GameRequest;
import ccy.civilizationleaderboard.game.dto.GameResponse;
import ccy.civilizationleaderboard.game.mapper.GameRequestMapper;
import ccy.civilizationleaderboard.game.mapper.GameResponseMapper;
import ccy.civilizationleaderboard.user.UserRepository;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final UserRepository userRepository;
    private final UserService userService;

    private final GameRepository gameRepository;
    private final GameRequestMapper GameRequestMapper;
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
    public GameResponse createGame(GameRequest postRequest) {

        Game game = GameRequestMapper.apply(postRequest);
        Game savedGame = gameRepository.save(game);

        return gameResponseMapper.apply(savedGame);
    }



    @Override
    public GameResponse editGame(int id, GameRequest putRequest) {

        Game game = GameRequestMapper.apply(putRequest);
        game.setId(id);

        Game updatedGame = gameRepository.save(game);

        return gameResponseMapper.apply(updatedGame);
    }



    @Override
    public void deleteGameBy(int id) {
        Game gameToDelete = gameRepository.findById(id).orElse(null);
        List<User> allUsers = userRepository.findAllByGameId(id);
        userService.updateUserGameTableOnGameDeletion(allUsers, gameToDelete);

        gameRepository.deleteById(id);

        userService.updateUserPlacementHistory(allUsers);
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

    @Override
    public List<GameResponse> getAllGamesByUsername(String username) {
        List<Game> gameList = gameRepository.findAllGamesByUsername(username);

        return gameList.stream()
                .map(gameResponseMapper)
                .toList();
    }
}
