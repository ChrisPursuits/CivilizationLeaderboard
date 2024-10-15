package ccy.civilizationleaderboard.civilization.model;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Civilization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private ccy.civilizationleaderboard.civilization.model.enums.Civilization name;//TODO perhaps make this to enum, for when adding played civilization history to User?

    @ElementCollection
    @CollectionTable(name = "leaders", joinColumns = @JoinColumn(name = "civilization_id"))
    private List<String> leaderList;

    @ManyToMany(mappedBy = "civilizationList")
    private List<GameStat> gameStatList;
}
