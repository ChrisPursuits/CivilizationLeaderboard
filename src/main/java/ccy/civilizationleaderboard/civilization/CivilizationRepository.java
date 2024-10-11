package ccy.civilizationleaderboard.civilization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CivilizationRepository extends JpaRepository<Civilization, Integer> {

    Optional<Civilization> findByCivilizationName(String civilizationName);
}
