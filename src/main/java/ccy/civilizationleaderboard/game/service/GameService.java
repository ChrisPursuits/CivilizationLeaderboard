package ccy.civilizationleaderboard.game.service;

import ccy.civilizationleaderboard.game.dto.GameRequest;
import ccy.civilizationleaderboard.game.dto.GameResponse;

import java.util.List;

public interface GameService {

    GameResponse getGameBy(int id);
    GameResponse createGame(GameRequest postRequest);
    GameResponse editGame(int id, GameRequest putRequest);
    void deleteGameBy(int id);


    boolean doesExist(int id);
    boolean doesExist(GameRequest postRequest);

    List<GameResponse> getAllGamesByUsername(String username);
}
