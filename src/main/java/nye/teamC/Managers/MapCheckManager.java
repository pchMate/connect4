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
        int lenW = map.length;
        int lenH = map[0].length;
        int checkSame = 3;

        for (int h = 0; h < lenH; h++)
        {
            for (int w = 0; w < lenW; w++)
            {
                if ((w + checkSame) >= lenW)
                {
                    continue;
                }
                if ((h + checkSame) >= lenH)
                {
                    continue;
                }
                var mapColor = map[h][w];
                var mapColor1 = map[h + 1][w + 1];
                var mapColor2 = map[h + 2][w + 2];
                var mapColor3 = map[h + 3][w + 3];
                if (mapColor == mapColor1
                        && mapColor1 == mapColor2
                        && mapColor2 == mapColor3
                        && mapColor != MapColor.None)
                {
                    return new Pair<>(true, mapColor);
                }
            }
        }

        for (int w = 0; w < lenW; w++)
        {
            for (int h = lenH - 1; h > 0; h--)
            {
                if ((h - checkSame) < 0)
                {
                    continue;
                }
                if ((w + checkSame) >= lenW)
                {
                    continue;
                }
                var mapColor = map[w][h];
                var mapColorMin1 = map[w + 1][h - 1];
                var mapColorMin2 = map[w + 2][h - 2];
                var mapColorMin3 = map[w + 3][h - 3];
                if (mapColor == mapColorMin1
                        && mapColorMin1 == mapColorMin2
                        && mapColorMin2 == mapColorMin3
                        && mapColor != MapColor.None)
                {
                    return new Pair<>(true, mapColor);
                }
            }
        }
        return new Pair<>(false, MapColor.None);
    }
}
