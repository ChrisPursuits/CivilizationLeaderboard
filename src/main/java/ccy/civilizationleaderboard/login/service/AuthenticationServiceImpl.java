package ccy.civilizationleaderboard.login.service;

import ccy.civilizationleaderboard.config.service.JwtService;
import ccy.civilizationleaderboard.login.dto.AuthenticationRequest;
import ccy.civilizationleaderboard.login.dto.AuthenticationResponse;
import ccy.civilizationleaderboard.user.model.Role;
import ccy.civilizationleaderboard.user.model.User;
import ccy.civilizationleaderboard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(AuthenticationRequest registerRequest) {
        User userToRegister = User.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.ROLE_USER)
                .leaderboardList(new ArrayList<>())
                .gameList(new ArrayList<>())
                .gameStatList(new ArrayList<>())
                .totalGamesPlayed(0)
                .creationDate(new Date())
                .build();

        User registedUser = userRepository.save(userToRegister);
        return generatedAuthToken(registedUser);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest loginRequest) {
        String username = loginRequest.username();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        loginRequest.password()
                )
        );

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return generatedAuthToken(user);
    }





    private AuthenticationResponse generatedAuthToken(User user) {
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        String token = jwtService.generateToken(user, extraClaims);

        return new AuthenticationResponse(token);
    }
}
