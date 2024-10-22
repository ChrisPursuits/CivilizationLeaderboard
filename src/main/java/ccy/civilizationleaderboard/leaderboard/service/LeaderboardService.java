package ccy.civilizationleaderboard.leaderboard.service;

import ccy.civilizationleaderboard.invite.model.Invite;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;

import java.util.List;

public interface LeaderboardService {

    LeaderboardResponse getLeaderboardBy(int id);
    LeaderboardResponse createLeaderboard(LeaderboardRequest postRequest);
    LeaderboardResponse editLeaderboard(int id, LeaderboardRequest putRequest);
    void deleteLeaderboard(int id);

    boolean doesExist(int id);
    boolean doesExist(LeaderboardRequest postRequest);

    LeaderboardResponse addUserToLeaderboard(int leaderboardId, String username);
    void acceptedInvitation(Invite invite );

    List<LeaderboardResponse> getAllLeaderboardsByUsername(String username);
}
