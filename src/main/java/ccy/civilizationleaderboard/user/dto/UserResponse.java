package ccy.civilizationleaderboard.user.dto;


import java.util.List;

public record UserResponse(
        int id,
        String username,
        int totalGamesPlayed,
        List<Integer> placementHistory
) {
}
