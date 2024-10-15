package ccy.civilizationleaderboard.user.model;

import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int totalGamesPlayed;

    @ElementCollection
    @CollectionTable(name = "placement_history", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "placement")
    private List<Integer> placementHistory;

    //TODO
    //create a playedCivilizationTracker/history using a HashMap.


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leaderboard_id")
    private Leaderboard leaderboard;

    @OneToMany(mappedBy = "user")
    private List<Game> gameList;


    //used in AuthenticationServiceImpl for registering new users
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
