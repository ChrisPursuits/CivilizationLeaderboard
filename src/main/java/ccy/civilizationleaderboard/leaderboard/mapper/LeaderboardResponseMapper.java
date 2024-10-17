package ccy.civilizationleaderboard.leaderboard.mapper;

import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LeaderboardResponseMapper implements Function<Leaderboard, LeaderboardResponse> {

    @Override
    public LeaderboardResponse apply(Leaderboard leaderboard) {
        return new LeaderboardResponse(
                leaderboard.getId(),
                leaderboard.getName(),
                leaderboard.getDescription()
        );
    }
}
