package nye.teamC.Managers;

import nye.teamC.Map;
import nye.teamC.MapColor;
import java.io.FileWriter;
import java.io.FileReader;

public class SaveManager
{

    public static void SaveMap(Map map, String filename)
    {
        FileWriter file;
        try
        {
            file = new FileWriter(filename);
            int pW = map.GetWidth();
            int pH = map.GetHeight();
            file.write(pW);
            file.write(pH);
            for (int i = 0; i < pW; i++)
            {
                for (int j = 0; j < pH; j++)
                {
                    file.write(map.GetColor(i, j).name());
                    file.write(0x0);
                }
            }
            file.flush();
            file.close();
            System.out.println("Saving success!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public  static Map LoadMap(String filename)
    {
        Map map = null;
        try {
            var reader = new FileReader(filename);
            int pW = reader.read();
            if (pW == -1)
                return null;
            int pH = reader.read();
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
                    map.SetColor(i, j, MapColor.valueOf(name.toString()));
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
