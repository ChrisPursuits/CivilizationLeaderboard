package ccy.civilizationleaderboard.game.dto;

public record GameRequest(
        String title,
        String description,
        int finishingRound
) {
}
