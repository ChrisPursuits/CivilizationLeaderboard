package ccy.civilizationleaderboard.gamestat.dto;

import ccy.civilizationleaderboard.gamestat.Placement;

public record EditGameStat(
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
