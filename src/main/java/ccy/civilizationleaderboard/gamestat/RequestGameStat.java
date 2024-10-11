package ccy.civilizationleaderboard.gamestat;

public record RequestGameStat(
        int id,
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
