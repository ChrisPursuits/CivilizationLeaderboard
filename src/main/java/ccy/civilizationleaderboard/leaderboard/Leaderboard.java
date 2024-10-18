package ccy.civilizationleaderboard.leaderboard;

import ccy.civilizationleaderboard.invite.model.Invite;
import ccy.civilizationleaderboard.user.model.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @JsonManagedReference
    private List<User> leaderboardMembers;

    @OneToMany(mappedBy = "leaderboard")
    private List<Invite> invites;

    //used in LeaderboardRequestMapper to map from dto to model
    public Leaderboard(String name, String description) {
        this.name = name;
        this.description = description;
        this.leaderboardMembers = new ArrayList<>();
    }
}
