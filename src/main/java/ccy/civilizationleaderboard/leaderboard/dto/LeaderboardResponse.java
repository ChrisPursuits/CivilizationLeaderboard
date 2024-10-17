package ccy.civilizationleaderboard.leaderboard.dto;

import ccy.civilizationleaderboard.user.model.User;

import java.util.List;

public record LeaderboardResponse(
        int id,
        String name,
        String description
        ){

}
