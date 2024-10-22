package ccy.civilizationleaderboard.invite.dto;

import java.util.Date;

public record InviteResponse(
        int inviteId,
        String issuerUsername,
        String receiverUsername,
        Date issuedDate
) {
}
