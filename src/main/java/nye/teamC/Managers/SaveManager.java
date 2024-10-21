package nye.teamC.Managers;

import nye.teamC.Map;
import nye.teamC.MapColor;
import java.io.FileWriter;
import java.io.FileReader;

public final class SaveManager
{
    private SaveManager()
    {

    }

    /** Save Map
     * @param map Map File
     * @param filename Name Of the file
     * @return Returns false if failure, true if success
     */
    public static boolean saveMap(final Map map, final String filename)
    {
        if (map == null)
        {
            return false;
        }
        if (filename == null)
        {
            return false;
        }
        try
        {
            FileWriter file = new FileWriter(filename);
            int pW = map.getWidth();
            int pH = map.getHeight();
            file.write(pW);
            file.write(pH);
            for (int i = 0; i < pW; i++)
            {
                for (int j = 0; j < pH; j++)
                {
                    file.write(map.getColor(i, j).name());
                    file.write(0x0);
                }
            }
            file.flush();
            file.close();
            System.out.println("Saving success!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /** Load Map from File.
     * @param filename File Name to load from.
     * @return The Map if load success. Null if not.
     */
    public static Map loadMap(final String filename)
    {
        Map map = null;
        try {
            var reader = new FileReader(filename);
            int pW = reader.read();
            if (pW == -1)
            {
                return null;
            }
            int pH = reader.read();
            if (pH == -1)
            {
                return null;
            }
            map = new Map(pW, pH);
            for (int i = 0; i < pW; i++)
            {
                for (int j = 0; j < pH; j++)
                {
                    StringBuilder name = new StringBuilder();
                    int c = reader.read();
                    while (c != 0)
                    {
                        name.append((char) c);
                        c = reader.read();
                    }
                    map.setColor(i, j, MapColor.valueOf(name.toString()));
                }
            }
            reader.close();
            System.out.println("Loading success!");
            return map;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

}
