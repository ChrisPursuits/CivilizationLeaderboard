package ccy.civilizationleaderboard.leaderboard.dto;

import ccy.civilizationleaderboard.user.model.User;

import java.util.List;

public record LeaderboardResponse(
        int id,
        String name,
        String description,

        //TODO this should be a list containing DTOs as well.
        //at the moment idk how to fix this since the leaderboard model, should and is containing the User model as an attribute and not the DTO.
        //this is the problem, since the LeaderboardResponseMapper take a Leaderboard model, and maps it into a LeaderboardResponse.
        //the problem at the moment is that the mapper implements Function<model, dto> that overrides apply(). This method only take 1 argument not 2.
        //a potential fix would be to map the user list into a userDTO list, and give it to a method that could take this as the second argument.
        List<User> leaderboardMembers
        ){

}
