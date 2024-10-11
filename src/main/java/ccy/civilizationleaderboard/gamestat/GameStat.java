package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.civilization.Civilization;
import ccy.civilizationleaderboard.game.Game;
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

}
