package ccy.civilizationleaderboard.user;

import ccy.civilizationleaderboard.user.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserResponseMapper implements Function<User, UserResponse> {

    @Override
    public UserResponse apply(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getTotalGamesPlayed(),
                user.getPlacementHistory()
        );
    }
}
