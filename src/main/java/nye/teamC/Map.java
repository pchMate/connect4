package nye.teamC;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map
{
    MapColor[][] internalMap;
    protected int pWidth;
    protected  int pHeight;
    // sor -> Width
    // oszlop -> Height


    public Map(int Width, int Height) throws OutOfPlayableAreaException
    {
        if (!CheckWH(Width))
            throw new OutOfPlayableAreaException(String.format("Field Width (%d) is not acceptable", Width));
        if (!CheckWH(Height))
            throw new OutOfPlayableAreaException(String.format("Field Height (%d) is not acceptable", Height));
        pWidth = Width;
        pHeight = Height;
        internalMap = new MapColor[Width][Height];
        for (int i = 0; i < Width; i++)
        {
            for (int j = 0; j < Height; j++)
            {
                internalMap[i][j] = MapColor.None;
            }
        }
    }


    /**
     * Random egy oszlopba majd sorba teszi
     */
    public void SetAI()
    {
        //TODO: make it randomize
        Random r = new Random();
        int newHeight = r.nextInt(0, pHeight);
        int width = GetRightWidth(newHeight);
        if (width == -1)
            return;
        SetColor(width, newHeight, MapColor.Red);
    }

    /** Lerak egy "korongot" a megadott oszlophoz
     * @param Height Az oszlop a csusztatashoz
     * @return Igazat ad ha sikeres minden
     */
    public boolean SetPlayer(int Height)
    {
        int width = GetRightWidth(Height);
        if (width == -1)
        {
            System.out.println("You cannot place here!");
            return false;
        }
        return SetColor(width, Height, MapColor.Yellow);
    }

    /** Lekéri a legjobb sor szamat abban az oszlopban.
     * @param Height Oszlop
     * @return Legjobb sor vagy -1
     */
    public int GetRightWidth(int Height)
    {
        for (int Width = internalMap.length - 1; Width > 0; Width--)
        {
            if (CheckValid(Width, Height))
                return Width;
        }
        return -1;
    }

    /**
     * Kiir minden oszlopot, sort, "szepen"
     */
    public void PrintMap()
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


    /** Megnezi hogy az adott sor és oszlop kombinacio ures-e
     * @param Width Sor
     * @param Height Oszlop
     * @return Igaz ha üres
     */
    protected boolean CheckValid(int Width, int Height)
    {
        if (internalMap.length < Width)
            return false;
        if (internalMap[0].length < Height)
            return false;

        return internalMap[Width][Height] == MapColor.None;
    }

    /** Megnezi hogy le lehet rakni a szint, lerakja ha igen
     * @param Width Sor
     * @param Height Oszlop
     * @param color Szín
     * @return Igaz ha sikeres
     */
    protected boolean SetColor(int Width, int Height, MapColor color)
    {
        if (CheckValid(Width, Height))
        {
            internalMap[Width][Height] = color;
            return true;
        }
        return false;
    }

    /** Megnezi a szamot benne van e a hatarertekbe
     * @param number oszlop/sor
     * @return Igaz ha 4 < szam < 12
     */
    protected boolean CheckWH(int number)
    {
        if (number < 4)
            return false;
        if (number > 12)
            return false;
        return  true;
    }


    /** Megnezi hogy vizszintese, függölegesen, atlosan, megvan-e a 4.
     * @return Egy "Pair"-be az eredményt. Ha igaz akkor van és a nyertes a szin, ha nincs akkor szin is üres "Nonde"
     */
    Pair<Boolean, MapColor> Check4()
    {
        // vizstintes check:
        for (MapColor[] mapColors : internalMap)
        {
            MapColor prev_MapColor = MapColor.None;
            List<MapColor> Reds = new ArrayList<MapColor>();
            List<MapColor> Yellows = new ArrayList<MapColor>();
            for (MapColor mapColor : mapColors)
            {
                if (prev_MapColor != mapColor)
                {
                    prev_MapColor = mapColor;
                    Reds.clear();
                    Yellows.clear();
                }
                if (prev_MapColor == MapColor.Red)
                    Reds.add(mapColor);
                if (prev_MapColor == MapColor.Yellow)
                    Yellows.add(mapColor);
                // print for savety
                System.out.print(prev_MapColor + " " + mapColor + " " + Reds.size() + " " + Yellows.size() + "\n");
                if (Reds.size() == 4)
                    return new Pair<>(true, MapColor.Red);
                if (Yellows.size() == 4)
                    return new Pair<>(true, MapColor.Yellow);
            }
            System.out.println();
        }

        return new Pair<>(false, MapColor.None);
    }

}