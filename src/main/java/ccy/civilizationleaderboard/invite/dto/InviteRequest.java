package ccy.civilizationleaderboard.invite.dto;

import java.util.Date;

public record InviteRequest(
        int leaderboardId,
        int issuerId,
        int receiverId
) {
}
