package ccy.civilizationleaderboard.gamestat.dto;

import ccy.civilizationleaderboard.civilization.model.Civilization;
import ccy.civilizationleaderboard.gamestat.model.Placement;

public record GameStatResponse(
        int id,
        int userId,
        Civilization civilization,
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
