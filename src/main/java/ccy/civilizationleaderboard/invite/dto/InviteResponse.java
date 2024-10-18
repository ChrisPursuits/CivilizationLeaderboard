package ccy.civilizationleaderboard.invite.dto;

import java.util.Date;

public record InviteResponse(
        int inviteId,
        int issuerId,
        Date issuedDate
) {
}
