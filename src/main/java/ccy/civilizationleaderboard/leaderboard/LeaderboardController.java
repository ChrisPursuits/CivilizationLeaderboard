package ccy.civilizationleaderboard.leaderboard;

import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;
import ccy.civilizationleaderboard.leaderboard.service.LeaderboardService;
import ccy.civilizationleaderboard.requestvalidator.EntityType;
import ccy.civilizationleaderboard.requestvalidator.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;
    private final RequestValidator requestValidator;


    @GetMapping("/leaderboard/{id}")
    public ResponseEntity<LeaderboardResponse> getLeaderboard(@PathVariable int id) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.GET, EntityType.LEADERBOARD, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        LeaderboardResponse response = leaderboardService.getLeaderboardBy(id);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/leaderboard")
    public ResponseEntity<LeaderboardResponse> createLeaderboard(@RequestBody LeaderboardRequest postRequest) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.POST, EntityType.LEADERBOARD, postRequest);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        LeaderboardResponse response = leaderboardService.createLeaderboard(postRequest);
        return ResponseEntity.ok(response);
    }



    @PutMapping("/leaderboard/{id}")
    public ResponseEntity<LeaderboardResponse> updateLeaderboard(@PathVariable int id, @RequestBody LeaderboardRequest putRequest) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.PUT, EntityType.LEADERBOARD, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        LeaderboardResponse response = leaderboardService.editLeaderboard(id, putRequest);
        return ResponseEntity.ok(response);

    }



    @DeleteMapping("/leaderboard/{id}")
    public ResponseEntity<Void> deleteLeaderboard(@PathVariable int id) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.DELETE, EntityType.LEADERBOARD, id);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        leaderboardService.deleteLeaderboard(id);
        return ResponseEntity.noContent().build();
    }
}
