package ccy.civilizationleaderboard.invite.mapper;

import ccy.civilizationleaderboard.invite.dto.InviteRequest;
import ccy.civilizationleaderboard.invite.model.Invite;
import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.LeaderboardRepository;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InviteRequestMapper implements Function<InviteRequest, Invite> {

    private final UserRepository userRepository;
    private final LeaderboardRepository leaderboardRepository;

    @Override
    public Invite apply(InviteRequest inviteRequest) {

        User issuer = userRepository.findById(inviteRequest.issuerId()).get();
        User receiver = userRepository.findById(inviteRequest.receiverId()).get();
        Leaderboard leaderboard = leaderboardRepository.findById(inviteRequest.leaderboardId()).get();

        return Invite.builder()
                .leaderboard(leaderboard)
                .issuer(issuer)
                .receiver(receiver)
                .issuedDate(new Date())
                .build();
    }
}
