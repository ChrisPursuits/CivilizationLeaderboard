package ccy.civilizationleaderboard.user.service;


import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.UserResponse;
import ccy.civilizationleaderboard.user.model.User;

import java.util.List;

public interface UserService {

   User findUserBy(String username);
   List<UserResponse> getAllUsersByLeaderboardIdSorted(int leaderboardId);
   void updateUserPlacementHistory(GameStat gameStat);
}
