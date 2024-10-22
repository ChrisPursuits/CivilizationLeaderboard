package ccy.civilizationleaderboard.invite.controller;

import ccy.civilizationleaderboard.invite.dto.InviteRequest;
import ccy.civilizationleaderboard.invite.dto.InviteResponse;
import ccy.civilizationleaderboard.invite.service.InviteService;
import ccy.civilizationleaderboard.leaderboard.service.LeaderboardService;
import ccy.civilizationleaderboard.requestvalidator.EntityType;
import ccy.civilizationleaderboard.requestvalidator.RequestValidator;
import ccy.civilizationleaderboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InviteController {

    private final LeaderboardService leaderboardService;
    private final RequestValidator requestValidator;
    private final InviteService inviteService;
    private final UserService userService;


    @GetMapping("/invites/{receiverId}")
    public ResponseEntity<List<InviteResponse>> getAllInvitesByReceiverId(@PathVariable int receiverId) {

        boolean doesExist = userService.doesExist(receiverId);
        if (!doesExist) {
            throw new UsernameNotFoundException("User not found");
        }

        List<InviteResponse> response = inviteService.getAllInvites(receiverId);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/invite")
    public ResponseEntity<InviteResponse> sendInvite(@RequestBody InviteRequest inviteRequest) {

        boolean doesReceiverExist = userService.doesExist(inviteRequest.receiverUsername());
        if (!doesReceiverExist) {
            return ResponseEntity.notFound().build();
        }

        boolean alreadyInLeaderboard = leaderboardService.isUserInLeaderboard(inviteRequest.leaderboardId(), inviteRequest.receiverUsername());
        if (alreadyInLeaderboard) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        ResponseEntity<Void> validationResponse = requestValidator.validateRequest(HttpMethod.POST, EntityType.INVITE, inviteRequest);
        if (validationResponse != null) {
            return ResponseEntity
                    .status(validationResponse.getStatusCode())
                    .build();
        }

        InviteResponse response = inviteService.sendInvite(inviteRequest);
        return ResponseEntity.ok(response);
    }



    @PutMapping("/invite/accept/{inviteId}")
    public ResponseEntity<String> acceptInvite(@PathVariable int inviteId) {
        String message = inviteService.acceptInvite(inviteId);
        return ResponseEntity.ok(message);
    }



    @PutMapping("/invite/decline/{inviteId}")
    public ResponseEntity<String> declineInvite(@PathVariable int inviteId) {
        String message = inviteService.declineInvite(inviteId);
        return ResponseEntity.ok(message);
    }
}
