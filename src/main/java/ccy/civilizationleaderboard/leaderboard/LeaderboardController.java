package ccy.civilizationleaderboard.leaderboard;

import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;
import ccy.civilizationleaderboard.leaderboard.service.LeaderboardService;
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
public class LeaderboardController {

    private final UserService userService;
    private final RequestValidator requestValidator;
    private final LeaderboardService leaderboardService;



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


    @GetMapping("/leaderboard/user/{username}")
    public ResponseEntity<List<LeaderboardResponse>> getLeaderboardsByUsername(@PathVariable String username) {

        boolean doesExist = userService.doesExist(username);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        List<LeaderboardResponse> response = leaderboardService.getAllLeaderboardsByUsername(username);
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


    //TODO
    //consider removing this endpoint as it forcefully adds a user to a leaderboard, might be fun to have for an admin, lol.
    @PostMapping("/leaderboard/{leaderboardId}")
    public ResponseEntity<LeaderboardResponse> addUserToLeaderboard(@PathVariable int leaderboardId,
                                                                    @RequestParam String username) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.GET, EntityType.LEADERBOARD, leaderboardId);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        boolean doesExist = userService.doesExist(username);
        if (!doesExist) {
            return ResponseEntity.notFound().build();
        }

        LeaderboardResponse response = leaderboardService.addUserToLeaderboard(leaderboardId, username);
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
