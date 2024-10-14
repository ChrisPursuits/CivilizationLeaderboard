package ccy.civilizationleaderboard.leaderboard;

import ccy.civilizationleaderboard.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    @OneToMany(mappedBy = "leaderboard")
    private Set<User> user;

    //used in LeaderboardRequestMapper to map from dto to model
    public Leaderboard(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
