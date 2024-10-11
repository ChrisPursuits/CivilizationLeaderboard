package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.dto.CreateGameStat;
import ccy.civilizationleaderboard.gamestat.dto.EditGameStat;
import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.service.GameStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gamestat")
@RequiredArgsConstructor
public class GameStatController {

    private final GameStatService gameStatService;


    @GetMapping("/{gameStatId}")
    public ResponseEntity<GameStatResponse> getGameStatById(@PathVariable int gameStatId) {
        GameStatResponse gameStatResponse = gameStatService.getGameStatBy(gameStatId);

        if (gameStatResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gameStatResponse);
    }


    @GetMapping("s/{gameId}")
    public ResponseEntity<GameStatResponse> getAllGameStatsByGameId(@PathVariable int gameId) {
        return null;
    }


    @PostMapping("")
    public ResponseEntity<GameStatResponse> createGameStat(@RequestBody CreateGameStat createRequest) {

        boolean doesExist = gameStatService.doesGameStatExist(createRequest);
        if (doesExist) {
            return ResponseEntity.badRequest().build();
        }

        GameStatResponse gameStatResponse = gameStatService.createGameStat(createRequest);
        return ResponseEntity.ok(gameStatResponse);
    }


    @PutMapping("")
    public ResponseEntity<GameStatResponse> updateGameStat(@RequestBody EditGameStat editRequest) {
        boolean doesExist = gameStatService.doesGameStatExist(editRequest);

        if (doesExist) {
            return ResponseEntity.badRequest().build();
        }

        GameStatResponse gameStatResponse = gameStatService.editGameStat(editRequest);
        return ResponseEntity.ok(gameStatResponse);
    }


    @DeleteMapping("/{gameStatId}")
    public ResponseEntity<Void> deleteGameStat(@PathVariable Integer gameStatId) {

        if (gameStatId == null || gameStatId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        boolean doesExist = gameStatService.doesGameStatExist(gameStatId);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        gameStatService.deleteGameStatBy(gameStatId);
        return ResponseEntity.noContent().build();
    }
}
