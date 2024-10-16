package ccy.civilizationleaderboard.leaderboard.service;

import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;

public interface LeaderboardService {

    LeaderboardResponse getLeaderboardBy(int id);
    LeaderboardResponse createLeaderboard(LeaderboardRequest postRequest);
    LeaderboardResponse editLeaderboard(int id, LeaderboardRequest putRequest);
    void deleteLeaderboard(int id);

    boolean doesExist(int id);
    boolean doesExist(LeaderboardRequest postRequest);
    LeaderboardResponse addUserToLeaderboard(int leaderboardId, int userId);
}
