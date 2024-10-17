package ccy.civilizationleaderboard.user.service;


import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.UserResponse;
import ccy.civilizationleaderboard.user.model.User;

import java.util.List;

public interface UserService {

   List<UserResponse> getAllUsersByLeaderboardIdSorted(int leaderboardId);
   List<User> findAllByGameId(int gameId);

   void updateGameHistory(GameStat gameStat);
   void updateUserPlacementHistory(GameStat gameStat);
   void updateUserPlacementHistory(User user);
   void updateUserPlacementHistory(List<User> allUsers);
   void updateUserGameTableOnGameDeletion(List<User> allUsers, Game gameToDelete);

   boolean doesExist(int id);
   boolean doesExist(String username);
}
