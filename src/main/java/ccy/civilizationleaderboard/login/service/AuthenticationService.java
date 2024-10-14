package ccy.civilizationleaderboard.login.service;

import ccy.civilizationleaderboard.login.dto.AuthenticationRequest;
import ccy.civilizationleaderboard.login.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(AuthenticationRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest loginRequest);
}
