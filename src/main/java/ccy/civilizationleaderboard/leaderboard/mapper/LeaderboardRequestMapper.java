package ccy.civilizationleaderboard.leaderboard.mapper;

import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.dto.LeaderboardRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LeaderboardRequestMapper implements Function<LeaderboardRequest, Leaderboard> {
    @Override
    public Leaderboard apply(LeaderboardRequest leaderboardRequest) {
        return new Leaderboard(
                leaderboardRequest.name(),
                leaderboardRequest.description()
        );
    }
}
