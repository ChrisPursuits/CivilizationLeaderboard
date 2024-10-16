package ccy.civilizationleaderboard.gamestat.model;

import ccy.civilizationleaderboard.civilization.model.Civilization;
import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.user.model.User;
import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "civilization_id")
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
    public GameStat(User user, Civilization civilization, Placement placement, int victoryPoints, int militaryPoints, int sciencePoints, int culturePoints, int gold, int religiousPoints, int diplomaticPoints) {
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

