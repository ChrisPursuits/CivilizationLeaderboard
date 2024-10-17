package ccy.civilizationleaderboard.user.controller;

import ccy.civilizationleaderboard.requestvalidator.EntityType;
import ccy.civilizationleaderboard.requestvalidator.RequestValidator;
import ccy.civilizationleaderboard.user.dto.UserResponse;
import ccy.civilizationleaderboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final RequestValidator requestValidator;



    @GetMapping("/users/{leaderboardId}")
    public ResponseEntity<List<UserResponse>> getAllSortedUsersByLeaderboardId(@PathVariable int leaderboardId) {

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.GET, EntityType.LEADERBOARD, leaderboardId);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        List<UserResponse> response = userService.getAllUsersByLeaderboardIdSorted(leaderboardId);
        return ResponseEntity.ok(response);
    }
}
