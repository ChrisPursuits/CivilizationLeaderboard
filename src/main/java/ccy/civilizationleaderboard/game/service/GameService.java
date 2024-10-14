package ccy.civilizationleaderboard.game.service;

import ccy.civilizationleaderboard.game.dto.GameRequest;
import ccy.civilizationleaderboard.game.dto.GameResponse;

public interface GameService {
    //crud
    GameResponse getGameBy(int id);
    GameResponse createGame(GameRequest postRequest);
    GameResponse editGame(int id, GameRequest putRequest);
    void deleteGameBy(int id);

    //other
    boolean doesExist(int id);
    boolean doesExist(GameRequest postRequest);
}
