package ccy.civilizationleaderboard.user.service;

import ccy.civilizationleaderboard.user.User;
import ccy.civilizationleaderboard.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Optional<User> findUserBy(String username) {
      return userRepository.findByUsername(username);
    }
}
