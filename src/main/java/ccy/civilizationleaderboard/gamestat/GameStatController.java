package ccy.civilizationleaderboard.gamestat;

import ccy.civilizationleaderboard.gamestat.service.GameStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GameStatController {

    private final GameStatService gameStatService;


}
