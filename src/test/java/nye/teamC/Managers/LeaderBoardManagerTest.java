package nye.teamC.Managers;

import nye.teamC.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderBoardManagerTest
{
    @BeforeEach
    void setUp()
    {
        LeaderBoardManager.clear();
        var score = new File("scoreboard.txt");
        if (score.exists())
        {
            score.delete();
        }
    }

    @AfterAll
    static void CleanupScore()
    {
        var score = new File("scoreboard.txt");
        if (score.exists())
        {
            score.delete();
        }
    }

    @Test
    void refresh()
    {
        assertThrows(FileNotFoundException.class, () -> {LeaderBoardManager.refresh();});
        LeaderBoardManager.addNewWin("test");
        assertDoesNotThrow(() -> {LeaderBoardManager.save();});
        assertDoesNotThrow(() -> {LeaderBoardManager.refresh();});
    }

    @Test
    void save()
    {
        assertDoesNotThrow(() -> {LeaderBoardManager.save();});
        assertDoesNotThrow(() -> {LeaderBoardManager.save();});
    }

    @Test
    void getWinner()
    {
        assertEquals(0, LeaderBoardManager.getWinner("test"));
        LeaderBoardManager.addNewWin("test");
        assertEquals(1, LeaderBoardManager.getWinner("test"));
        LeaderBoardManager.addNewWin("test");
        assertEquals(2, LeaderBoardManager.getWinner("test"));
    }

    @Test
    void addNewWin()
    {
        assertDoesNotThrow(() -> {LeaderBoardManager.addNewWin("test");});
    }

    @Test
    void ultimateTest()
    {
        assertThrows(FileNotFoundException.class, () -> {LeaderBoardManager.refresh();});
        LeaderBoardManager.addNewWin("test");
        LeaderBoardManager.addNewWin("test2");
        LeaderBoardManager.addNewWin("test");
        assertDoesNotThrow(() -> {LeaderBoardManager.save();});
        assertEquals(1, LeaderBoardManager.getWinner("test2"));
        assertEquals(2, LeaderBoardManager.getWinner("test"));
        assertDoesNotThrow(() -> {LeaderBoardManager.refresh();});
        assertEquals(1, LeaderBoardManager.getWinner("test2"));
        assertEquals(2, LeaderBoardManager.getWinner("test"));
    }
}
