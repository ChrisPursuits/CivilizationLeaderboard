package ccy.civilizationleaderboard.invite.mapper;

import ccy.civilizationleaderboard.invite.dto.InviteResponse;
import ccy.civilizationleaderboard.invite.model.Invite;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InviteResponseMapper implements Function<Invite, InviteResponse> {

    @Override
    public InviteResponse apply(Invite invite) {
        return new InviteResponse(
                invite.getId(),
                invite.getIssuer().getId(),
                invite.getIssuedDate()
        );
    }
}
