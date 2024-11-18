package nye.teamC.Managers;

import nye.teamC.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SaveManagerTest
{
    Map map;
    @BeforeEach
    void setUp()
    {
        map = new Map(6,6);
    }

    @AfterAll
    static void CleanupTest()
    {
        var test = new File("Test");
        if (test.exists())
        {
            if (test.delete())
            {
                System.out.println("Test Delete Success!");
            }
        }
    }

    @Test
    void saveMap()
    {
        assertFalse(SaveManager.saveMap(map, null));
        assertFalse(SaveManager.saveMap(null, null));
        assertFalse(SaveManager.saveMap(null, "Test"));
        assertTrue(SaveManager.saveMap(map, "Test"));
        assertFalse(SaveManager.saveMap(map, "Test/tes/te"));
    }

    @Test
    void loadMap() throws IOException
    {
        assertTrue(SaveManager.saveMap(map, "Test"));
        var newMap = SaveManager.loadMap("Test");
        assertNotNull(newMap);
        newMap = SaveManager.loadMap(null);
        assertNull(newMap);
        FileWriter f = new FileWriter("Test");
        f.close();
        newMap = SaveManager.loadMap("Test");
        assertNull(newMap);
        f = new FileWriter("Test");
        f.write(1);
        f.close();
        newMap = SaveManager.loadMap("Test");
        assertNull(newMap);
    }
}