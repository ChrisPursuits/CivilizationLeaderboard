package ccy.civilizationleaderboard.civilization.model;

import ccy.civilizationleaderboard.gamestat.model.GameStat;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Enumerated(EnumType.STRING)
    private ccy.civilizationleaderboard.civilization.model.enums.Civilization civilization;

    @Column(unique = true)
    private String leader;

    @OneToMany(mappedBy = "selectedCivilization")
    @JsonBackReference
    private List<GameStat> gameStatList;

    public Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization civilization, String leader) {
        this.civilization = civilization;
        this.leader = leader;
    }
}
