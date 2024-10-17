package ccy.civilizationleaderboard.user.service;


import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.UserResponse;
import ccy.civilizationleaderboard.user.model.User;

import java.util.List;

public interface UserService {

   List<UserResponse> getAllUsersByLeaderboardIdSorted(int leaderboardId);

   void updateGameHistory(GameStat gameStat);
   void updateUserPlacementHistory(GameStat gameStat);

   boolean doesExist(int id);
   boolean doesExist(String username);
}
