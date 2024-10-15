package ccy.civilizationleaderboard.leaderboard;

import ccy.civilizationleaderboard.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "leaderboard_users",
            joinColumns = @JoinColumn(name = "leaderboard_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList;

    //used in LeaderboardRequestMapper to map from dto to model
    public Leaderboard(String name, String description) {
        this.name = name;
        this.description = description;
        this.userList = new ArrayList<>();
    }
}
