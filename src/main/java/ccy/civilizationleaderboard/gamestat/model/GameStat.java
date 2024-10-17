package ccy.civilizationleaderboard.gamestat.model;

import ccy.civilizationleaderboard.civilization.model.Civilization;
import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.user.model.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonManagedReference
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "civilization_id")
    @JsonManagedReference
    private Civilization selectedCivilization;

    @Enumerated(EnumType.STRING)
    private Placement placement;

    private int victoryPoints;
    private int militaryPoints;
    private int sciencePoints;
    private int culturePoints;
    private int gold;
    private int religiousPoints;
    private int diplomaticPoints;



    //used in GameStatRequestMapper
    public GameStat(Game game, User user, Civilization civilization, Placement placement, int victoryPoints, int militaryPoints, int sciencePoints, int culturePoints, int gold, int religiousPoints, int diplomaticPoints) {
        this.game = game;
        this.user = user;
        this.selectedCivilization = civilization;
        this.placement = placement;
        this.victoryPoints = victoryPoints;
        this.militaryPoints = militaryPoints;
        this.sciencePoints = sciencePoints;
        this.culturePoints = culturePoints;
        this.gold = gold;
        this.religiousPoints = religiousPoints;
        this.diplomaticPoints = diplomaticPoints;
    }
}

