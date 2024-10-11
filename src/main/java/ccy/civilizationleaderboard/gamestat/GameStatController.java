package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.dto.GameStatResponse;
import ccy.civilizationleaderboard.gamestat.service.GameStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gamestat")
@RequiredArgsConstructor
public class GameStatController {

    private final GameStatService gameStatService;


    @GetMapping("/{gameStatId}")
    public ResponseEntity<GameStatResponse> getGameStatById(@PathVariable int gameStatId) {

        GameStatResponse gameStatResponse = gameStatService.getGameStatById(gameStatId);

        if (gameStatResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gameStatResponse);
    }
}
