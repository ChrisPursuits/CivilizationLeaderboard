package ccy.civilizationleaderboard.login.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
