package ccy.civilizationleaderboard.invite.model;

import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "leaderboard_id")
    private Leaderboard leaderboard;

    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private User issuer;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private Date issuedDate;
    private Date respondedDate;
    private InviteStatus status;

}
