package nye.teamC.Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public final class LeaderBoardManager
{
    private LeaderBoardManager()
    {

    }

    private static final HashMap<String, Integer> WINNERS = new HashMap<>();

    public static void refresh() throws FileNotFoundException
    {
        var scoreboard = new File("scoreboard.txt");
        if (!scoreboard.exists())
        {
            // File not exist to read from.
            // System.out.println("Cannot load scoreboard.txt!");
            throw new FileNotFoundException("scoreboard.txt file not exist!");
        }
        Scanner myReader = new Scanner(scoreboard);
        boolean wasName = false;
        String name = "";
        while (myReader.hasNextLine())
        {
            String data = myReader.nextLine();
            if (wasName)
            {
                int val = Integer.parseInt(data);
                WINNERS.put(name, val);
                wasName = false;
            }
            else
            {
                name = data;
                wasName = true;
            }
        }
        myReader.close();
    }

    public static void save() throws IOException
    {
        var scoreboard = new File("scoreboard.txt");
        if (scoreboard.createNewFile())
        {
            System.out.println("New ScoreBoard created!");
        }
        FileWriter myWriter = new FileWriter(scoreboard);
        for (Map.Entry<String, Integer> entry : WINNERS.entrySet())
        {
            String k = entry.getKey();
            Integer v = entry.getValue();
            myWriter.write(k);
            myWriter.write("\n");
            myWriter.write(v.toString());
            myWriter.write("\n");
        }
        myWriter.close();
    }

    public static int getWinner(final String name)
    {
        if (WINNERS.containsKey(name))
        {
            return WINNERS.get(name);
        }
        return 0;
    }

    public static void addNewWin(final String name)
    {
        if (WINNERS.containsKey(name))
        {
            var wins = WINNERS.get(name);
            wins++;
            WINNERS.replace(name, wins);
        }
        else
        {
            WINNERS.put(name, 1);
        }
    }

    public static void clear()
    {
        WINNERS.clear();
    }

    public static void print()
    {
        System.out.println("Név - Nyert körök száma");
        for (Map.Entry<String, Integer> entry : WINNERS.entrySet())
        {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println(k + " - " + v);
        }
    }
}
