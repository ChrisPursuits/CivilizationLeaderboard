package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.service.GameStatService;
import ccy.civilizationleaderboard.requestvalidator.EntityType;
import ccy.civilizationleaderboard.requestvalidator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/gamestat")
@RequiredArgsConstructor
public class GameStatController {

    private final GameStatService gameStatService;
    private final RequestValidator requestValidator;


    @GetMapping("/{id}")
    public ResponseEntity<GameStatResponse> getGameStatById(@PathVariable Integer id) {


        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.GET, EntityType.GAMESTAT, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        GameStatResponse gameStatResponse = gameStatService.getGameStatBy(id);
        return ResponseEntity.ok(gameStatResponse);
    }


    @GetMapping
    public ResponseEntity<Set<GameStatResponse>> getAllGameStatsByGameId(@RequestParam Integer gameId) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.GET, EntityType.GAME, gameId);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        Set<GameStatResponse> allGameStats = gameStatService.getAllGameStatsByGameId(gameId);
        return ResponseEntity.ok(allGameStats);
    }


    @PostMapping
    public ResponseEntity<GameStatResponse> createGameStat(@RequestBody GameStatRequest postRequest) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.POST, EntityType.GAMESTAT, postRequest);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        GameStatResponse gameStatResponse = gameStatService.createGameStat(postRequest);
        return ResponseEntity.ok(gameStatResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<GameStatResponse> updateGameStat(@PathVariable int id, @RequestBody GameStatRequest putRequest) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.PUT, EntityType.GAMESTAT, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        GameStatResponse gameStatResponse = gameStatService.editGameStat(id, putRequest);
        return ResponseEntity.ok(gameStatResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameStat(@PathVariable Integer id) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.DELETE, EntityType.GAMESTAT, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status( validationResponse.getStatusCode() )
                    .build();
        }

        gameStatService.deleteGameStatBy(id);
        return ResponseEntity.noContent().build();
    }
}
