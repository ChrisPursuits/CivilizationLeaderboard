package ccy.civilizationleaderboard.game;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private int finishingRound;

    @ManyToMany(mappedBy = "gameList")
    private List<User> userList;

    @OneToMany(mappedBy = "game")
    @JsonBackReference
    private List<GameStat> gameStatList;

    //used in GameRequestMapper
    public Game(String title, String description) {
        this.title = title;
        this.description = description;
        this.gameStatList = new ArrayList<>();
    }

    //used for inserting test data into db
    public Game(String title, String description, int finishingRound) {
        this.title = title;
        this.description = description;
        this.finishingRound = finishingRound;
        this.gameStatList = new ArrayList<>();
    }
}