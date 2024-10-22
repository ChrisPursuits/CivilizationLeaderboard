package ccy.civilizationleaderboard.invite.dto;

import java.util.Date;

public record InviteRequest(
        int leaderboardId,
        String issuerUsername,
        String receiverUsername
) {
}
