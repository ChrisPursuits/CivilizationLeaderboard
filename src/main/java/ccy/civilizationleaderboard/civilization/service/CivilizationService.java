package ccy.civilizationleaderboard.civilization.service;

import ccy.civilizationleaderboard.civilization.CivilizationResponse;

public interface CivilizationService {
    CivilizationResponse getCivilizationBy(int id);
    CivilizationResponse getCivilizationBy(String name);
}
