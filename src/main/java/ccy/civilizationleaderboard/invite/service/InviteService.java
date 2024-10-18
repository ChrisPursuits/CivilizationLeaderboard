package ccy.civilizationleaderboard.invite.service;

import ccy.civilizationleaderboard.invite.dto.InviteRequest;
import ccy.civilizationleaderboard.invite.dto.InviteResponse;

import java.util.List;

public interface InviteService {

    List<InviteResponse> getAllInvites(int receiverId);
    InviteResponse sendInvite(InviteRequest postRequest);
    String declineInvite(int inviteId);
    String acceptInvite(int inviteId);

    boolean doesExist(InviteRequest postRequestBody);
}
