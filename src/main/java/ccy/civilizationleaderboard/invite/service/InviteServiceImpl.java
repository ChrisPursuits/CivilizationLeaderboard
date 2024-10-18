package ccy.civilizationleaderboard.invite.service;

import ccy.civilizationleaderboard.exception.InviteNotFoundException;
import ccy.civilizationleaderboard.invite.dto.InviteRequest;
import ccy.civilizationleaderboard.invite.dto.InviteResponse;
import ccy.civilizationleaderboard.invite.mapper.InviteRequestMapper;
import ccy.civilizationleaderboard.invite.mapper.InviteResponseMapper;
import ccy.civilizationleaderboard.invite.model.Invite;
import ccy.civilizationleaderboard.invite.model.InviteStatus;
import ccy.civilizationleaderboard.invite.repository.InviteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {

    private final InviteRepository inviteRepository;
    private final InviteResponseMapper inviteResponseMapper;
    private final InviteRequestMapper inviteRequestMapper;


    @Override
    public List<InviteResponse> getAllInvites(int receiverId) {
        List<Invite> invites = inviteRepository.findAllByReceiverId(receiverId);

        return invites.stream()
                .map(inviteResponseMapper)
                .toList();
    }

    @Override
    public InviteResponse sendInvite(InviteRequest postRequest) {
        Invite invite = inviteRequestMapper.apply(postRequest);

        Invite sentInvite = inviteRepository.save(invite);

        return inviteResponseMapper.apply(sentInvite);
    }

    @Override
    public String declineInvite(int inviteId) {
        Optional<Invite> inviteOptional = inviteRepository.findById(inviteId);

        if (inviteOptional.isEmpty()) {
         throw new InviteNotFoundException("Can't decline the invite.\nInvite with id: " + inviteId + " not found");
        }

        Invite invite = inviteOptional.get();
        invite.setStatus(InviteStatus.DECLINED);
        invite.setRespondedDate(new Date());
        inviteRepository.save(invite);

        return "Invite has been declined.";
    }

    @Override
    public String acceptInvite(int inviteId) {
        Optional<Invite> inviteOptional = inviteRepository.findById(inviteId);

        if (inviteOptional.isEmpty()) {
            throw new InviteNotFoundException("Can't decline the invite.\nInvite with id: " + inviteId + " not found");
        }

        Invite invite = inviteOptional.get();
        invite.setStatus(InviteStatus.ACCEPTED);
        invite.setRespondedDate(new Date());
        inviteRepository.save(invite);

        return "Invite has been accepted.";
    }

    @Override
    public boolean doesExist(InviteRequest postRequestBody) {
        return inviteRepository.existsByIssuer_IdAndReceiver_IdAndStatus(
                postRequestBody.issuerId(),
                postRequestBody.receiverId(),
                InviteStatus.PENDING);
    }
}