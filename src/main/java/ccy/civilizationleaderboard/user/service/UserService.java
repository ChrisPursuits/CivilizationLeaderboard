package ccy.civilizationleaderboard.user.service;


import ccy.civilizationleaderboard.user.User;

import java.util.Optional;

public interface UserService {
   User findUserBy(String username);
}
