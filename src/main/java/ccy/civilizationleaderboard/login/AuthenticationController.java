package ccy.civilizationleaderboard.login;

import ccy.civilizationleaderboard.login.dto.AuthenticationRequest;
import ccy.civilizationleaderboard.login.dto.AuthenticationResponse;
import ccy.civilizationleaderboard.login.service.AuthenticationService;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest registerRequest) {

        User user = userService.findUserBy(registerRequest.username());
        //username already taken
        if (user != null) {
            return ResponseEntity.badRequest().build();
        }

        AuthenticationResponse response = authenticationService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest loginRequest) {

        AuthenticationResponse response;
        try {
            response = authenticationService.authenticate(loginRequest);

        }catch (Exception exception) {
            //wrong credentials or username to be precise, but only tell user that it could be username or password.
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(response);
    }
}

