package ccy.civilizationleaderboard.game;

import ccy.civilizationleaderboard.game.dto.GameRequest;
import ccy.civilizationleaderboard.game.dto.GameResponse;
import ccy.civilizationleaderboard.game.service.GameService;
import ccy.civilizationleaderboard.requestvalidator.EntityType;
import ccy.civilizationleaderboard.requestvalidator.RequestValidator;
import ccy.civilizationleaderboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GameController {

    private final UserService userService;
    private final GameService gameService;
    private final RequestValidator requestValidator;


    @GetMapping("/game/{id}")
    public ResponseEntity<GameResponse> getGameById(@PathVariable int id) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.GET, EntityType.GAME, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        GameResponse response = gameService.getGameBy(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/games")
    public ResponseEntity<List<GameResponse>> getGames(@RequestParam String username) {

        boolean doesExist = userService.doesExist(username);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        List<GameResponse> response = gameService.getAllGamesByUsername(username);
        return ResponseEntity.ok(response);
    }






    @PostMapping("/game/user/{username}")
    public ResponseEntity<GameResponse> createGame(@PathVariable String username,
                                                   @RequestBody GameRequest postRequest) {

        boolean doesExist = userService.doesExist(username);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.POST, EntityType.GAME, postRequest);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        GameResponse response = gameService.createGame(username, postRequest);
        return ResponseEntity.ok(response);
    }






    @PutMapping("/game/{id}")
    public ResponseEntity<GameResponse> updateGame(@PathVariable int id, @RequestBody GameRequest putRequest) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.PUT, EntityType.GAME, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        GameResponse response = gameService.editGame(id, putRequest);
        return ResponseEntity.ok(response);
    }






    //TODO FIX DELETE
    @DeleteMapping("/game/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.DELETE, EntityType.GAME, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        gameService.deleteGameBy(id);
        return ResponseEntity.noContent().build();
    }
}
