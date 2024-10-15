package ccy.civilizationleaderboard.user;

import ccy.civilizationleaderboard.game.dto.GameResponse;

import java.util.List;

public record UserResponse(
        String username,
        int totalGamesPlayed,
        List<Integer> placementHistory
) {
}
