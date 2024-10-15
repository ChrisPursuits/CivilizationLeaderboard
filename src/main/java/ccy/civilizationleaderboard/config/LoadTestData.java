package ccy.civilizationleaderboard.config;

import ccy.civilizationleaderboard.civilization.CivilizationRepository;
import ccy.civilizationleaderboard.civilization.model.Civilization;
import ccy.civilizationleaderboard.game.Game;
import ccy.civilizationleaderboard.game.GameRepository;
import ccy.civilizationleaderboard.gamestat.GameStatRepository;
import ccy.civilizationleaderboard.gamestat.model.GameStat;
import ccy.civilizationleaderboard.gamestat.model.Placement;
import ccy.civilizationleaderboard.leaderboard.Leaderboard;
import ccy.civilizationleaderboard.leaderboard.LeaderboardRepository;
import ccy.civilizationleaderboard.user.UserRepository;
import ccy.civilizationleaderboard.user.model.Role;
import ccy.civilizationleaderboard.user.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class LoadTestData implements CommandLineRunner {

    private final LeaderboardRepository leaderboardRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameStatRepository gameStatRepository;
    private final CivilizationRepository civilizationRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        createCivilizations();
        createLeaderboard();
        createUsers();
        createConnectionBetweenLeaderboardAndUser();
        createGames();
        createConnectionBetweenUserAndGame();
        createGameStats();

        createConnectionBetweenGameAndGameStat();
    }



    private void createCivilizations() {
        Civilization americanCiv1 = Civilization.builder().civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.AMERICAN).leader("Teddy Roosevelt").build();
        Civilization americanCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.AMERICAN, "Teddy Roosevelt (Bull Moose)");
        Civilization americanCiv3 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.AMERICAN, "Teddy Roosevelt (Rough Rider)");
        Civilization americanCiv4 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.AMERICAN, "Abraham Lincoln");

        Civilization arabianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ARABIAN, "Saladin (Vizier)");
        Civilization arabianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ARABIAN, "Saladin (Sultan)");

        Civilization australianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.AUSTRALIAN, "John Curtin");

        Civilization aztecCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.AZTEC, "Montezuma");

        Civilization babylonianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.BABYLONIAN, "Hammurabi");

        Civilization brazilianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.BRAZILIAN, "Pedro II");

        Civilization byzantineCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.BYZANTINE, "Basil II");
        Civilization byzantineCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.BYZANTINE, "Theodora");

        Civilization canadianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CANADIAN, "Wilfrid Laurier");

        Civilization chineseCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CHINESE, "Qin Shi Huang (Mandate of Heaven)");
        Civilization chineseCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CHINESE, "Qin Shi Huang (Unifier)");
        Civilization chineseCiv3 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CHINESE, "Kublai Khan (Chinese)");
        Civilization chineseCiv4 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CHINESE, "Yongle");
        Civilization chineseCiv5 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CHINESE, "Wu Zetian");

        Civilization creeCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.CREE, "Poundmaker");

        Civilization dutchCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.DUTCH, "Wilhelmina");

        Civilization egyptianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.EGYPTIAN, "Cleopatra (Egyptian)");
        Civilization egyptianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.EGYPTIAN, "Cleopatra (Ptolemaic)");
        Civilization egyptianCiv3 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.EGYPTIAN, "Ramses II");

        Civilization englishCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ENGLISH, "Victoria (Age of Empire)");
        Civilization englishCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ENGLISH, "Victoria (Age of Steam)");
        Civilization englishCiv3 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ENGLISH, "Eleanor of Aquitaine (English)");
        Civilization englishCiv4 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ENGLISH, "Elizabeth I");

        Civilization ethiopianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ETHIOPIAN, "Menelik II");

        Civilization frenchCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.FRENCH, "Catherine de Medici (Black Queen)");
        Civilization frenchCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.FRENCH, "Catherine de Medici (Magnificence)");
        Civilization frenchCiv3 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.FRENCH, "Eleanor of Aquitaine (French)");

        Civilization gallicCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GALLIC, "Ambiorix");

        Civilization georgianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GEORGIAN, "Tamar");

        Civilization germanCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GERMAN, "Frederick Barbarossa");
        Civilization germanCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GERMAN, "Ludwig II");

        Civilization granColombianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GRAN_COLUMBIAN, "Simón Bolívar");

        Civilization greekCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GREEK, "Pericles");
        Civilization greekCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.GREEK, "Gorgo");

        Civilization hungarianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.HUNGARIAN, "Matthias Corvinus");

        Civilization incanCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.INCAN, "Pachacuti");

        Civilization indianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.INDIAN, "Gandhi");
        Civilization indianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.INDIAN, "Chandragupta");

        Civilization indonesianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.INDONESIAN, "Gitarja");

        Civilization japaneseCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.JAPANESE, "Hojo Tokimune");
        Civilization japaneseCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.JAPANESE, "Tokugawa");

        Civilization khmerCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.KHMER, "Jayavarman VII");

        Civilization kongoleseCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.KONGOLESE, "Mvemba a Nzinga");
        Civilization kongoleseCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.KONGOLESE, "Nzinga Mbande");

        Civilization koreanCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.KOREAN, "Seondeok");
        Civilization koreanCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.KOREAN, "Sejong");

        Civilization macedonianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MACEDONIAN, "Alexander");

        Civilization malianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MALIAN, "Mansa Musa");
        Civilization malianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MALIAN, "Sundiata Keita");

        Civilization mãoriCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MÃORI, "Kupe");

        Civilization mapucheCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MAPUCHE, "Lautaro");

        Civilization mayanCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MAYAN, "Lady Six Sky");

        Civilization mongolianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MONGOLIAN, "Genghis Khan");
        Civilization mongolianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.MONGOLIAN, "Kublai Khan (Mongolian)");

        Civilization norwegianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.NORWEGIAN, "Harald Hardrada (Konge)");
        Civilization norwegianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.NORWEGIAN, "Harald Hardrada (Varangian)");

        Civilization nubianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.NUBIAN, "Amanitore");

        Civilization ottomanCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.OTTOMAN, "Suleiman (Kanuni)");
        Civilization ottomanCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.OTTOMAN, "Suleiman (Muhteşem)");

        Civilization persianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.PERSIAN, "Cyrus");
        Civilization persianCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.PERSIAN, "Nader Shah");

        Civilization phoenicianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.PHOENICIAN, "Dido");

        Civilization polishCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.POLISH, "Jadwiga");

        Civilization portuguese = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.PORTUGUESE, "João III");

        Civilization romanCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ROMAN, "Trajan");
        Civilization romanCiv2 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ROMAN, "Julius Caesar");

        Civilization russianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.RUSSIAN, "Peter");

        Civilization scottishCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.SCOTTISH, "Robert the Bruce");

        Civilization scythianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.SCYTHIAN, "Tomyris");

        Civilization spanishCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.SPANISH, "Philip II");

        Civilization sumerianCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.SUMERIAN, "Gilgamesh");

        Civilization swedishCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.SWEDISH, "Kristina");

        Civilization vietnameseCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.VIETNAMESE, "Bà Triêu");

        Civilization zuluCiv1 = new Civilization(ccy.civilizationleaderboard.civilization.model.enums.Civilization.ZULU, "Shaka");



        civilizationRepository.save(americanCiv1); civilizationRepository.save(americanCiv2); civilizationRepository.save(americanCiv3); civilizationRepository.save(americanCiv4);
        civilizationRepository.save(arabianCiv1); civilizationRepository.save(arabianCiv2);
        civilizationRepository.save(australianCiv1);
        civilizationRepository.save(aztecCiv1);
        civilizationRepository.save(babylonianCiv1);
        civilizationRepository.save(brazilianCiv1);
        civilizationRepository.save(byzantineCiv1); civilizationRepository.save(byzantineCiv2);
        civilizationRepository.save(canadianCiv1);
        civilizationRepository.save(chineseCiv1); civilizationRepository.save(chineseCiv2); civilizationRepository.save(chineseCiv3); civilizationRepository.save(chineseCiv4); civilizationRepository.save(chineseCiv5);
        civilizationRepository.save(creeCiv1);
        civilizationRepository.save(dutchCiv1);
        civilizationRepository.save(egyptianCiv1); civilizationRepository.save(egyptianCiv2); civilizationRepository.save(egyptianCiv3);
        civilizationRepository.save(englishCiv1); civilizationRepository.save(englishCiv2); civilizationRepository.save(englishCiv3); civilizationRepository.save(englishCiv4);
        civilizationRepository.save(ethiopianCiv1);
        civilizationRepository.save(frenchCiv1); civilizationRepository.save(frenchCiv2); civilizationRepository.save(frenchCiv3);
        civilizationRepository.save(gallicCiv1);
        civilizationRepository.save(georgianCiv1);
        civilizationRepository.save(germanCiv1); civilizationRepository.save(germanCiv2);
        civilizationRepository.save(granColombianCiv1);
        civilizationRepository.save(greekCiv1); civilizationRepository.save(greekCiv2);
        civilizationRepository.save(hungarianCiv1);
        civilizationRepository.save(incanCiv1);
        civilizationRepository.save(indianCiv1); civilizationRepository.save(indianCiv2);
        civilizationRepository.save(indonesianCiv1);
        civilizationRepository.save(japaneseCiv1); civilizationRepository.save(japaneseCiv2);
        civilizationRepository.save(khmerCiv1);
        civilizationRepository.save(kongoleseCiv1); civilizationRepository.save(kongoleseCiv2);
        civilizationRepository.save(koreanCiv1); civilizationRepository.save(koreanCiv2);
        civilizationRepository.save(macedonianCiv1);
        civilizationRepository.save(malianCiv1); civilizationRepository.save(malianCiv2);
        civilizationRepository.save(mãoriCiv1);
        civilizationRepository.save(mapucheCiv1);
        civilizationRepository.save(mayanCiv1);
        civilizationRepository.save(mongolianCiv1); civilizationRepository.save(mongolianCiv2);
        civilizationRepository.save(norwegianCiv1); civilizationRepository.save(norwegianCiv2);
        civilizationRepository.save(nubianCiv1);
        civilizationRepository.save(ottomanCiv1); civilizationRepository.save(ottomanCiv2);
        civilizationRepository.save(persianCiv1); civilizationRepository.save(persianCiv2);
        civilizationRepository.save(phoenicianCiv1);
        civilizationRepository.save(polishCiv1);
        civilizationRepository.save(portuguese);
        civilizationRepository.save(romanCiv1); civilizationRepository.save(romanCiv2);
        civilizationRepository.save(russianCiv1);
        civilizationRepository.save(scottishCiv1);
        civilizationRepository.save(scythianCiv1);
        civilizationRepository.save(spanishCiv1);
        civilizationRepository.save(sumerianCiv1);
        civilizationRepository.save(swedishCiv1);
        civilizationRepository.save(vietnameseCiv1);
        civilizationRepository.save(zuluCiv1);
    }



    private void createLeaderboard() {
        Leaderboard leaderboard = new Leaderboard("First Leaderboard", "This is a test leaderboard");
        leaderboardRepository.save(leaderboard);
    }



    private void createUsers() {
        User chris = User.builder()
                .username("Chris")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_USER)
                .build();

        User markus = User.builder()
                .username("Markus")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_USER)
                .build();

        User engjëll = User.builder()
                .username("Engjëll")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_USER)
                .build();

        User mikkel = User.builder()
                .username("Mikkel")
                .password(passwordEncoder.encode("123"))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(chris); userRepository.save(markus); userRepository.save(engjëll); userRepository.save(mikkel);
    }



    private void createConnectionBetweenLeaderboardAndUser() {
        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(1);
        Leaderboard leaderboard = leaderboardOptional.get();

        leaderboard.getUserList().addAll(userRepository.findAll());
    }



    private void createGames() {
        Game game1 = new Game("Game 1", "This is a test game");
        Game game2 = new Game("Game 2", "This is a test game");

        gameRepository.save(game1); gameRepository.save(game2);
    }



    private void createConnectionBetweenUserAndGame() {
        Optional<Game> game1 = gameRepository.findById(1);
        Optional<Game> game2 = gameRepository.findById(2);

        Optional<User> userOptional1 = userRepository.findById(1);
        Optional<User> userOptional2 = userRepository.findById(2);
        Optional<User> userOptional3 = userRepository.findById(3);
        Optional<User> userOptional4 = userRepository.findById(4);
        User chris = userOptional1.get();
        User markus = userOptional2.get();
        User engjëll = userOptional3.get();
        User mikkel = userOptional4.get();

        chris.getGameList().addLast(game1.get());
        markus.getGameList().addLast(game1.get());
        engjëll.getGameList().addLast(game1.get());
        mikkel.getGameList().addLast(game1.get());

        userRepository.save(chris); userRepository.save(markus); userRepository.save(engjëll); userRepository.save(mikkel);

        chris.getGameList().addLast(game2.get());
        markus.getGameList().addLast(game2.get());
        engjëll.getGameList().addLast(game2.get());
        mikkel.getGameList().addLast(game2.get());

        userRepository.save(chris); userRepository.save(markus); userRepository.save(engjëll); userRepository.save(mikkel);
    }



    private void createGameStats() {

        Civilization yongle = civilizationRepository.findByCivilizationName("Yongle").get();
        Civilization joãoIII = civilizationRepository.findByCivilizationName("João III").get();
        Civilization victoriaAgeOfEmpire = civilizationRepository.findByCivilizationName("Victoria (Age of Empire)").get();
        Civilization shaka = civilizationRepository.findByCivilizationName("Shaka").get();

        GameStat chirsGameStat1 = GameStat.builder()
                .selectedCivilization(yongle)
                .placement(Placement.FIRST)
                .victoryPoints(500)
                .militaryPoints(500)
                .sciencePoints(500)
                .culturePoints(500)
                .gold(500)
                .religiousPoints(500)
                .diplomaticPoints(500)
                .build();

        GameStat markusGameStat1 = GameStat.builder()
                .selectedCivilization(joãoIII)
                .placement(Placement.SECOND)
                .victoryPoints(300)
                .militaryPoints(300)
                .sciencePoints(300)
                .culturePoints(300)
                .gold(300)
                .religiousPoints(300)
                .diplomaticPoints(300)
                .build();

        GameStat engjëllGameStat1 = GameStat.builder()
                .selectedCivilization(victoriaAgeOfEmpire)
                .placement(Placement.THIRD)
                .victoryPoints(200)
                .militaryPoints(200)
                .sciencePoints(200)
                .culturePoints(200)
                .gold(200)
                .religiousPoints(200)
                .diplomaticPoints(200)
                .build();

        GameStat mikkelGameStat1 = GameStat.builder()
                .selectedCivilization(shaka)
                .placement(Placement.FOURTH)
                .victoryPoints(100)
                .militaryPoints(100)
                .sciencePoints(100)
                .culturePoints(100)
                .gold(100)
                .religiousPoints(100)
                .diplomaticPoints(100)
                .build();

        gameStatRepository.save(chirsGameStat1); gameStatRepository.save(markusGameStat1); gameStatRepository.save(engjëllGameStat1); gameStatRepository.save(mikkelGameStat1);


        GameStat chirsGameStat2 = GameStat.builder()
                .selectedCivilization(yongle)
                .placement(Placement.FIRST)
                .victoryPoints(500)
                .militaryPoints(500)
                .sciencePoints(500)
                .culturePoints(500)
                .gold(500)
                .religiousPoints(500)
                .diplomaticPoints(500)
                .build();

        GameStat markusGameStat2 = GameStat.builder()
                .selectedCivilization(joãoIII)
                .placement(Placement.SECOND)
                .victoryPoints(300)
                .militaryPoints(300)
                .sciencePoints(300)
                .culturePoints(300)
                .gold(300)
                .religiousPoints(300)
                .diplomaticPoints(300)
                .build();

        GameStat engjëllGameStat2 = GameStat.builder()
                .selectedCivilization(victoriaAgeOfEmpire)
                .placement(Placement.THIRD)
                .victoryPoints(200)
                .militaryPoints(200)
                .sciencePoints(200)
                .culturePoints(200)
                .gold(200)
                .religiousPoints(200)
                .diplomaticPoints(200)
                .build();

        GameStat mikkelGameStat2 = GameStat.builder()
                .selectedCivilization(shaka)
                .placement(Placement.FOURTH)
                .victoryPoints(100)
                .militaryPoints(100)
                .sciencePoints(100)
                .culturePoints(100)
                .gold(100)
                .religiousPoints(100)
                .diplomaticPoints(100)
                .build();

        gameStatRepository.save(chirsGameStat2); gameStatRepository.save(markusGameStat2); gameStatRepository.save(engjëllGameStat2); gameStatRepository.save(mikkelGameStat2);
    }



    private void createConnectionBetweenGameAndGameStat() {
        Optional<Game> gameOptional1 = gameRepository.findById(1);
        Optional<Game> gameOptional2 = gameRepository.findById(2);
        Game game1 = gameOptional1.get();
        Game game2 = gameOptional2.get();

        List<GameStat> gameStatsForGame1 = new ArrayList<>(List.of(
                gameStatRepository.findById(1).get(),
                gameStatRepository.findById(2).get(),
                gameStatRepository.findById(3).get(),
                gameStatRepository.findById(4).get())
        );
        List<GameStat> gameStatsForGame2 = new ArrayList<>(List.of(
                gameStatRepository.findById(5).get(),
                gameStatRepository.findById(6).get(),
                gameStatRepository.findById(7).get(),
                gameStatRepository.findById(8).get())
        );

        game1.getGameStatList().addAll(gameStatsForGame1);
        game2.getGameStatList().addAll(gameStatsForGame2);

        gameRepository.save(game1);
        gameRepository.save(game2);
    }



}
