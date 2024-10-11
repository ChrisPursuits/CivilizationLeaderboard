package ccy.civilizationleaderboard.civilization;

public interface CivilizationService {
    RequestCivilization getCivilizationBy(int id);
    RequestCivilization getCivilizationBy(String name);
}
