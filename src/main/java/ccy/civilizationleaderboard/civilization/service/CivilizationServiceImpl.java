package ccy.civilizationleaderboard.civilization.service;

import ccy.civilizationleaderboard.civilization.CivilizationRepository;
import ccy.civilizationleaderboard.civilization.CivilizationResponse;
import ccy.civilizationleaderboard.civilization.CivilizationResponseMapper;
import ccy.civilizationleaderboard.civilization.model.Civilization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CivilizationServiceImpl implements CivilizationService {

    private final CivilizationRepository civilizationRepository;
    private final CivilizationResponseMapper civilizationResponseMapper;

    @Override
    public CivilizationResponse getCivilizationBy(int id) {
        Optional<Civilization> civilizationOptional = civilizationRepository.findById(id);

        if (civilizationOptional.isEmpty()) {
            return null;
        }

        Civilization civilization = civilizationOptional.get();
        CivilizationResponse civilizationResponse = civilizationResponseMapper.apply(civilization);
        return civilizationResponse;
    }

    @Override
    public CivilizationResponse getCivilizationBy(String name) {
        Optional<Civilization> civilizationOptional = civilizationRepository.findCivilizationByLeader(name);

        if (civilizationOptional.isEmpty()) {
            return null;
        }

        Civilization civilization = civilizationOptional.get();
        CivilizationResponse civilizationResponse = civilizationResponseMapper.apply(civilization);
        return civilizationResponse;
    }
}
