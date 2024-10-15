package ccy.civilizationleaderboard.civilization.service;

import ccy.civilizationleaderboard.civilization.RequestCivilization;

public interface CivilizationService {
    RequestCivilization getCivilizationBy(int id);
    RequestCivilization getCivilizationBy(String name);
}
