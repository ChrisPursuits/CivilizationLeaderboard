package ccy.civilizationleaderboard.gamestat.dto;

import ccy.civilizationleaderboard.civilization.model.Civilization;
import ccy.civilizationleaderboard.gamestat.model.Placement;
import ccy.civilizationleaderboard.user.model.User;

public record GameStatRequest(
        User user,
        Civilization selectedCivilization,
        Placement placement,
        int victoryPoints,
        int militaryPoints,
        int sciencePoints,
        int culturePoints,
        int gold,
        int religiousPoints,
        int diplomaticPoints
) {
}
