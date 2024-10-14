package ccy.civilizationleaderboard.requestvalidator;

import ccy.civilizationleaderboard.game.service.GameService;
import ccy.civilizationleaderboard.game.dto.GameRequest;
import ccy.civilizationleaderboard.gamestat.dto.GameStatRequest;
import ccy.civilizationleaderboard.gamestat.service.GameStatService;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.service.LeaderboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class RequestValidator {

    private final LeaderboardService leaderboardService;
    private final GameService gameService;
    private final GameStatService gameStatService;

    public ResponseEntity<Void> validateRequest(HttpMethod method, EntityType entity, Object requestData) {
        boolean doesExist;

        if (requestData == null) {
            return ResponseEntity.badRequest().build();
        }


        if (method == HttpMethod.GET || method == HttpMethod.PUT || method == HttpMethod.DELETE) {
            //ensures that request data is in fact of type Integer
            if (!(requestData instanceof Integer id)) {
                return ResponseEntity.badRequest().build();
            }

            //checks existence by id.
            doesExist = doesExistById(entity, id);

            if (!doesExist && method == HttpMethod.PUT) {
                return ResponseEntity.badRequest().build();
            }

            if (!doesExist) {
                return ResponseEntity.notFound().build();
            }
        }


        if (method == HttpMethod.POST) {

            //checks existence by RequestBody.
            doesExist = doesExistByRequestBody(entity, requestData);
            if (doesExist) {
                return ResponseEntity.badRequest().build();
            }
        }

        return null;
    }


    private boolean doesExistById(EntityType entity, Integer id) {

        return switch (entity) {
            case LEADERBOARD -> leaderboardService.doesExist(id);
            case GAME -> gameService.doesExist(id);
            case GAMESTAT -> gameStatService.doesExist(id);
        };
    }


    private boolean doesExistByRequestBody(EntityType entity, Object requestBody) {

        return switch (entity) {
            case LEADERBOARD -> requestBody instanceof LeaderboardRequest && leaderboardService.doesExist((LeaderboardRequest) requestBody);
            case GAME -> requestBody instanceof GameRequest && gameService.doesExist((GameRequest) requestBody);
            case GAMESTAT -> requestBody instanceof GameStatRequest && gameStatService.doesExist((GameStatRequest) requestBody);
        };
    }
}
