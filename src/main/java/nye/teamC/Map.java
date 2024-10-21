package nye.teamC;

import nye.teamC.Managers.MapCheckManager;
import org.javatuples.Pair;

import java.util.Random;

public class Map
{
    private final MapColor[][] internalMap;
    private final int pWidth;
    private final int pHeight;
    private static final int MAP_MIN = 4;
    private static final int MAP_MAX = 12;
    // sor -> Width
    // oszlop -> Height


    public Map(final int width, final int height) throws OutOfPlayableAreaException
    {
        if (!checkWH(width))
        {
            throw new OutOfPlayableAreaException(
                    String.format("Field Width (%d) is not acceptable", width));
        }
        if (!checkWH(height))
        {
            throw new OutOfPlayableAreaException(
                    String.format("Field Height (%d) is not acceptable", height));
        }
        pWidth = width;
        pHeight = height;
        internalMap = new MapColor[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                internalMap[i][j] = MapColor.None;
            }
        }
    }


    /**
     * Random egy oszlopba majd sorba teszi
     */
    public void setAI()
    {
        Random r = new Random();
        int newHeight = r.nextInt(0, pHeight);
        int width = getRightWidth(newHeight);
        if (width == -1)
        {
            return;
        }
        setColor(width, newHeight, MapColor.Red);
    }

    /** Lerak egy "korongot" a megadott oszlophoz
     * @param height Az oszlop a csusztatashoz
     * @return Igazat ad ha sikeres minden
     */
    public boolean setPlayer(final int height)
    {
        int width = getRightWidth(height);
        if (width == -1)
        {
            System.out.println("You cannot place here!");
            return false;
        }
        return setColor(width, height, MapColor.Yellow);
    }

    /** Lekéri a legjobb sor szamat abban az oszlopban.
     * @param height Oszlop
     * @return Legjobb sor vagy -1
     */
    public int getRightWidth(final int height)
    {
        for (int width = internalMap.length - 1; width > 0; width--)
        {
            if (checkValid(width, height))
            {
                return width;
            }
        }
        return -1;
    }

    /**
     * Kiir minden oszlopot, sort
     */
    public void printMap()
    {
        System.out.println();
        for (MapColor[] mapColors : internalMap)
        {
            for (MapColor mapColor : mapColors)
            {
                System.out.print(mapColor + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
     * Kiir minden oszlopot, sort, "szepen"
     */
    public void printMapFancy()
    {
        System.out.println();
        for (MapColor[] mapColors : internalMap)
        {
            for (MapColor mapColor : mapColors)
            {
                System.out.print(mapColor.name().charAt(0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    /** Megnezi hogy az adott sor és oszlop kombinacio ures-e
     * @param width Sor
     * @param height Oszlop
     * @return Igaz ha üres
     */
    protected boolean checkValid(final int width, final int height)
    {
        if (internalMap.length < width)
        {
            return false;
        }
        if (internalMap[0].length < height)
        {
            return false;
        }

        return internalMap[width][height] == MapColor.None;
    }

    /** Megnezi hogy le lehet rakni a szint, lerakja ha igen
     * @param width Sor
     * @param height Oszlop
     * @param color Szín
     * @return Igaz ha sikeres
     */
    public boolean setColor(final int width, final int height, final MapColor color)
    {
        if (checkValid(width, height))
        {
            internalMap[width][height] = color;
            return true;
        }
        return false;
    }

    /** Megnezi a szamot benne van e a hatarertekbe
     * @param number oszlop/sor
     * @return Igaz ha 4 < szam < 12
     */
    protected boolean checkWH(final int number)
    {
        if (number < MAP_MIN)
        {
            return false;
        }
        return number <= MAP_MAX;
    }


    /** Megnezi hogy vizszintese, függölegesen, atlosan, megvan-e a 4.
     * @return Egy "Pair"-be az eredményt.
     * Ha igaz akkor van és a nyertes a szin, ha nincs akkor szin is üres "None"
     */
    public Pair<Boolean, MapColor> check4()
    {
        var vertical = MapCheckManager.checkVertical(internalMap);
        if (vertical.getValue0())
        {
            return vertical;
        }
        var horizontal = MapCheckManager.checkHorizontal(internalMap);
        if (horizontal.getValue0())
        {
            return vertical;
        }
        var diagonal = MapCheckManager.checkDiagonal(internalMap);
        if (diagonal.getValue0())
        {
            return vertical;
        }
        return new Pair<>(false, MapColor.None);
    }

    /** Get the Height of the Map
     * @return the Height
     */
    public int getHeight()
    {
        return pHeight;
    }

    /** Get the Width of the Map
     * @return the Width
     */
    public int getWidth()
    {
        return pWidth;
    }

    /** Getting the color in the Exact Place.
     * @param width Width of the Map
     * @param height Height of the Map
     * @return color from the Place or None
     */
    public MapColor getColor(final int width, final int height)
    {
        if (internalMap.length < width)
        {
            return MapColor.None;
        }
        if (internalMap[0].length < height)
        {
            return MapColor.None;
        }

        return internalMap[width][height];
    }

    /** Get the Internal Map Copy
     * @return Internal map Clone.
     */
    public MapColor[][] getInternalMapCopy()
    {
        return internalMap.clone();
    }
}
