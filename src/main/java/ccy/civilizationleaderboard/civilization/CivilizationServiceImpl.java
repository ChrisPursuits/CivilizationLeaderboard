package ccy.civilizationleaderboard.civilization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CivilizationServiceImpl implements CivilizationService {

    private final CivilizationRepository civilizationRepository;
    private final RequestCivilizationMapper requestCivilizationMapper;

    @Override
    public RequestCivilization getCivilizationBy(int id) {
        Optional<Civilization> civilizationOptional = civilizationRepository.findById(id);

        if (civilizationOptional.isEmpty()) {
            return null;
        }

        Civilization civilization = civilizationOptional.get();
        RequestCivilization requestCivilization = requestCivilizationMapper.apply(civilization);
        return requestCivilization;
    }

    @Override
    public RequestCivilization getCivilizationBy(String name) {
        Optional<Civilization> civilizationOptional = civilizationRepository.findByCivilizationName(name);

        if (civilizationOptional.isEmpty()) {
            return null;
        }

        Civilization civilization = civilizationOptional.get();
        RequestCivilization requestCivilization = requestCivilizationMapper.apply(civilization);
        return requestCivilization;
    }
}
