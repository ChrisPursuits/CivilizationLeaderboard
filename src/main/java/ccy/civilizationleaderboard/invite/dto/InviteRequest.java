package ccy.civilizationleaderboard.invite.dto;

import java.util.Date;

public record InviteRequest(
        int issuerId,
        int receiverId,
        Date issuedDate
) {
}
