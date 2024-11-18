package nye.teamC.Managers;

import nye.teamC.MapColor;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Map Checker.
 */
public final class MapCheckManager
{
    private MapCheckManager()
    {

    }

    private static final int MAX_TO_WIN = 4;
    /** Check Horizontal Wins
     * @param map Internal Map
     * @return Is The game Should End | MapColor Who win
     */
    public static Pair<Boolean, MapColor> checkHorizontal(final MapColor[][] map)
    {
        for (MapColor[] mapColors : map)
        {
            MapColor prevMapColor = MapColor.None;
            List<MapColor> reds = new ArrayList<>();
            List<MapColor> yellows = new ArrayList<>();
            for (MapColor mapColor : mapColors)
            {
                if (prevMapColor != mapColor)
                {
                    prevMapColor = mapColor;
                    reds.clear();
                    yellows.clear();
                }
                if (prevMapColor == MapColor.Red)
                {
                    reds.add(mapColor);
                }

                if (prevMapColor == MapColor.Yellow)
                {
                    yellows.add(mapColor);
                }
                if (reds.size() == MAX_TO_WIN)
                {
                    return new Pair<>(true, MapColor.Red);
                }
                if (yellows.size() == MAX_TO_WIN)
                {
                    return new Pair<>(true, MapColor.Yellow);
                }
            }
        }
        return new Pair<>(false, MapColor.None);
    }

    /** Check Vertical Wins
     * @param map Internal Map
     * @return Is The game Should End | MapColor Who win
     */
    public static Pair<Boolean, MapColor> checkVertical(final MapColor[][] map)
    {
        int lenW = map.length;
        int lenH = map[0].length;
        for (int h = 0; h < lenH; h++)
        {
            MapColor prevMapColor = MapColor.None;
            List<MapColor> reds = new ArrayList<>();
            List<MapColor> yellows = new ArrayList<>();
            for (int w = 0; w < lenW; w++)
            {
                var mapColor = map[w][h];
                if (prevMapColor != mapColor)
                {
                    prevMapColor = mapColor;
                    reds.clear();
                    yellows.clear();
                }
                if (prevMapColor == MapColor.Red)
                {
                    reds.add(mapColor);
                }

                if (prevMapColor == MapColor.Yellow)
                {
                    yellows.add(mapColor);
                }
                if (reds.size() == MAX_TO_WIN)
                {
                    return new Pair<>(true, MapColor.Red);
                }
                if (yellows.size() == MAX_TO_WIN)
                {
                    return new Pair<>(true, MapColor.Yellow);
                }
            }
        }
        return new Pair<>(false, MapColor.None);
    }

    /** Check Diagonal Wins
     * @param map Internal Map
     * @return Is The game Should End | MapColor Who win
     */
    public static Pair<Boolean, MapColor> checkDiagonal(final MapColor[][] map)
    {
        return new Pair<>(false, MapColor.None);
    }
}
