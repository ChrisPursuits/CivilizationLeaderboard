package ccy.civilizationleaderboard.game;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
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
    private List<GameStat> gameStatList;

    //used in GameRequestMapper
    public Game(String title, String description) {
        this.title = title;
        this.description = description;
        this.gameStatList = new ArrayList<>();
    }
}