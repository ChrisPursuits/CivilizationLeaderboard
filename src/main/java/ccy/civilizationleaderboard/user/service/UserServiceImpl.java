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
    public User findUserBy(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return null;
        }

        User foundUser = userOptional.get();
        return foundUser;
    }
}
