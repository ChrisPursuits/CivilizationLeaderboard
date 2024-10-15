package ccy.civilizationleaderboard.user.service;


import ccy.civilizationleaderboard.user.model.User;

import java.util.List;

public interface UserService {
   User findUserBy(String username);
//   List<User> findAllUsersBy(int leaderboardId);
   List<User> calculatePlayerStatistics(int leaderboardId);
}
