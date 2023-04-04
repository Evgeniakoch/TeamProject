package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerTest {
    private Map<Player, List<Game>> initPlayer() {
        Map<Player, List<Game>> sup = new HashMap<>();

        Player player = new Player("Petya");
        String name = player.getName();
        GameStore googlePlay = new GameStore();

        Game mario = new Game("Mario", "arcade", googlePlay);
        Game warcraft = new Game("Warcraft", "strategy", googlePlay);
        Game heroes = new Game("Heroes3", "strategy", googlePlay);

        player.installGame(mario);
        player.installGame(warcraft);
        player.installGame(heroes);

        player.play(mario, 10);
        player.play(warcraft, 100);
        player.play(heroes, 50);

        List<Game> listGames = new ArrayList<>();
        listGames.add(mario);
        listGames.add(warcraft);
        listGames.add(heroes);
        sup.put(player, listGames);

        return sup;
    }

    @Test
    public void createNewPlayer() {
        String expected = "Petya";
        Player player = new Player(expected);

        String actual = player.getName();
        assertEquals(expected, actual);
    }
    @Test
    public void shouldThrowRuntimeExceptionIfGameNotInstalled() {
        Player player = new Player("Petya");
        GameStore gameStore = new GameStore();
        Game game = new Game("Mario", "arcade", gameStore);

        player.play(game, 5);

        assertThrows(RuntimeException.class, () -> player.play(game, 5));

    }
    @Test
    public void shouldSumAddPlayedTime() {
        Player player = new Player("Petya");
        GameStore gameStore = new GameStore();
        Game game = new Game("Mario", "arcade", gameStore);

        player.play(game, 5);
        int actual = player.play(game, 10);
        int expected = 15;

        assertEquals(expected, actual);
// Issue

    }
    @Test
    public void shouldNotChangePlayedTimeAfterReinstall() {
        Map<Player, List<Game>> playerMap = initPlayer();

        Player petya = playerMap.keySet().iterator().next();
        List<Game> games = playerMap.get(petya);

        int expectedTime = petya.sumGenre("strategy");
        petya.installGame(games.get(0));
        petya.installGame(games.get(1));
        petya.installGame(games.get(2));
        int actualTime = petya.sumGenre("strategy");

        assertEquals(expectedTime, actualTime);

    }

    @Test
    public void shouldSumGenreIfOneGame1() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }




    // другие ваши тесты

    @Test
    public void shouldReturnTimeMostPlayedByGenre() {
        Map<Player, List<Game>> playerMap = initPlayer();

        Player petya = playerMap.keySet().iterator().next();
        List<Game> games = playerMap.get(petya);

        Game i = petya.mostPlayerByGenre("strategy");
        String expected = "Warcraft";
        String actualName = i.getTitle();

        assertEquals(expected, actualName);

    }
}

