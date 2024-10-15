package ccy.civilizationleaderboard.user;


import java.util.List;

public record UserResponse(
        String username,
        int totalGamesPlayed,
        List<Integer> placementHistory
) {
}
