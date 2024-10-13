package ccy.civilizationleaderboard.gamestat.model;

import ccy.civilizationleaderboard.civilization.Civilization;
import ccy.civilizationleaderboard.game.Game;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class GameStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Placement placement;

    private int victoryPoints;
    private int militaryPoints;
    private int sciencePoints;
    private int culturePoints;
    private int gold;
    private int religiousPoints;
    private int diplomaticPoints;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "civilization_id")
    private Civilization civilization;


    //used in Create- and EditGameStatMapper
    public GameStat(int id, Placement placement, int victoryPoints, int militaryPoints, int sciencePoints, int culturePoints, int gold, int religiousPoints, int diplomaticPoints) {
        this.id = id;
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
