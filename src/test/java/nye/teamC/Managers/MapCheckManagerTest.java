package nye.teamC.Managers;

import nye.teamC.Map;
import nye.teamC.MapColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapCheckManagerTest
{
    Map map;
    @BeforeEach
    void setUp()
    {
        map = new Map(6, 6);
    }

    @Test
    void checkHorizontal()
    {
        var ret = MapCheckManager.checkHorizontal(map.getInternalMapCopy());
        assertFalse(ret.getValue0());
        map.setColor(0, 0, MapColor.Yellow);
        map.setColor(0, 1, MapColor.Yellow);
        map.setColor(0, 2, MapColor.Yellow);
        map.setColor(0, 3, MapColor.Yellow);
        ret = MapCheckManager.checkHorizontal(map.getInternalMapCopy());
        assertTrue(ret.getValue0());
        map = new Map(6,6);
        map.setColor(0, 0, MapColor.Red);
        map.setColor(0, 1, MapColor.Red);
        map.setColor(0, 2, MapColor.Red);
        map.setColor(0, 3, MapColor.Red);
        ret = MapCheckManager.checkHorizontal(map.getInternalMapCopy());
        assertTrue(ret.getValue0());
        map = new Map(6,6);
        map.setColor(0, 0, MapColor.Red);
        map.setColor(0, 1, MapColor.Red);
        map.setColor(0, 2, MapColor.Yellow);
        map.setColor(0, 3, MapColor.Yellow);
        ret = MapCheckManager.checkHorizontal(map.getInternalMapCopy());
        assertFalse(ret.getValue0());
    }

    @Test
    void checkVertical()
    {
        var ret = MapCheckManager.checkVertical(map.getInternalMapCopy());
        assertFalse(ret.getValue0());
        assertEquals(ret.getValue1(), MapColor.None);
        map.setColor(0, 0, MapColor.Yellow);
        map.setColor(1, 0, MapColor.Yellow);
        map.setColor(2, 0, MapColor.Yellow);
        map.setColor(3, 0, MapColor.Yellow);
        ret = MapCheckManager.checkVertical(map.getInternalMapCopy());
        assertTrue(ret.getValue0());
        assertEquals(ret.getValue1(), MapColor.Yellow);
        map = new Map(6,6);
        map.setColor(0, 0, MapColor.Red);
        map.setColor(1, 0, MapColor.Red);
        map.setColor(2, 0, MapColor.Red);
        map.setColor(3, 0, MapColor.Red);
        ret = MapCheckManager.checkVertical(map.getInternalMapCopy());
        assertTrue(ret.getValue0());
        assertEquals(ret.getValue1(), MapColor.Red);
        map = new Map(6,6);
        map.setColor(0, 0, MapColor.Red);
        map.setColor(1, 0, MapColor.Red);
        map.setColor(2, 0, MapColor.Yellow);
        map.setColor(3, 0, MapColor.Yellow);
        ret = MapCheckManager.checkVertical(map.getInternalMapCopy());
        assertFalse(ret.getValue0());
    }

    @Test
    void checkDiagonal()
    {
        var ret = MapCheckManager.checkDiagonal(map.getInternalMapCopy());
        assertFalse(ret.getValue0());
        assertEquals(ret.getValue1(), MapColor.None);
    }
}