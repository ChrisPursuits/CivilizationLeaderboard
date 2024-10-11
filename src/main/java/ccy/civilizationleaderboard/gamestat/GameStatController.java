package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.game.GameService;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.service.GameStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/gamestat")
@RequiredArgsConstructor
public class GameStatController {

    private final GameService gameService;
    private final GameStatService gameStatService;


    @GetMapping("/{gameStatId}")
    public ResponseEntity<GameStatResponse> getGameStatById(@PathVariable Integer gameStatId) {

        ResponseEntity<Void> validationResponse = isPathVariableValid(gameStatId);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        GameStatResponse gameStatResponse = gameStatService.getGameStatBy(gameStatId);
        return ResponseEntity.ok(gameStatResponse);
    }


    //this method does not make use of the validation helper methods because they check the existence of gameStat.
    //this method checks for game and not gameStat.
    @GetMapping("/{gameId}")
    public ResponseEntity<Set<GameStatResponse>> getAllGameStatsByGameId(@PathVariable Integer gameId) {

        if (gameId == null || gameId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean doesExist = gameService.doesGameExist(gameId);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        Set<GameStatResponse> allGameStats = gameStatService.getAllGameStatsByGameId(gameId);
        return ResponseEntity.ok(allGameStats);
    }


    @PostMapping
    public ResponseEntity<GameStatResponse> createGameStat(@RequestBody GameStatRequest createRequest) {

        ResponseEntity<Void> validationResponse = isRequestBodyValid(createRequest);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        GameStatResponse gameStatResponse = gameStatService.createGameStat(createRequest);
        return ResponseEntity.ok(gameStatResponse);
    }


    @PutMapping
    public ResponseEntity<GameStatResponse> updateGameStat(@RequestBody GameStatRequest editRequest) {

        ResponseEntity<Void> validationResponse = isRequestBodyValid(editRequest);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        GameStatResponse gameStatResponse = gameStatService.editGameStat(editRequest);
        return ResponseEntity.ok(gameStatResponse);
    }


    @DeleteMapping("/{gameStatId}")
    public ResponseEntity<Void> deleteGameStat(@PathVariable Integer gameStatId) {

        ResponseEntity<Void> validationResponse = isPathVariableValid(gameStatId);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        gameStatService.deleteGameStatBy(gameStatId);
        return ResponseEntity.noContent().build();
    }






    private ResponseEntity<Void> isPathVariableValid(Integer pathVariable) {

        if (pathVariable == null || pathVariable <= 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean doesExist = gameStatService.doesGameStatExist(pathVariable);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        return null;
    }


    private ResponseEntity<Void> isRequestBodyValid(GameStatRequest requestBody) {

        if (requestBody == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean doesExist = gameStatService.doesGameStatExist(requestBody);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        return null;
    }
}
