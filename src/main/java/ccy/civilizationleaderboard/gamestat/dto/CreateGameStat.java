package ccy.civilizationleaderboard.gamestat.dto;

import ccy.civilizationleaderboard.gamestat.model.Placement;

public record CreateGameStat(
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
