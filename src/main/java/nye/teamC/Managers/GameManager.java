package nye.teamC.Managers;

import nye.teamC.Command.*;
import nye.teamC.Map;

import java.util.Scanner;

public class GameManager
{
    protected CommandManager CommandM;
    public Scanner in = new Scanner(System.in);
    String PlayerName;
    Map map;
    boolean IsGameEnded = false;

    public GameManager()
    {
        CommandM = new CommandManager();
    }

    public void Start()
    {
        System.out.println("Please Type your name to begin!");
        PlayerName = in.next();
        System.out.println("Commands available for your:");
        System.out.println("Commands Should be used as CommandName Enter Command Parameter (If there is no parameter just press enter)");
        CommandM.PrintCommands();
    }

    public void UseCommand(String command, String args)
    {
        if (IsGameEnded)
        {
            System.out.println("Game has been ended!");
        }
        ICommand iCommand = CommandM.GetCommand(command);
        if (iCommand instanceof QuitCommand)
        {
            IsGameEnded = true;
            System.out.println("quitting!");
            return;
        }
        if (iCommand instanceof IMapSetter setter)
        {
            setter.SetMap(map);
            System.out.println(iCommand.Execute(args) ? "OK" : "Wrong Parameter!");
        }
        if (iCommand instanceof IMapGetter getter)
        {
            System.out.println(iCommand.Execute(args) ? "OK" : "Wrong Parameter!");
            map = getter.GetMap();
        }
        if (map != null)
        {
            CheckWin();
            if (iCommand instanceof MoveCommand && !IsGameEnded)
            {
                map.SetAI();
                CheckWin();
            }
        }
        else
        {
            System.out.println("Please Load or Create a new Map!");
        }
    }

    void CheckWin()
    {
        var ret = map.Check4();
        if (ret.getValue0())
        {
            switch (ret.getValue1())
            {
                case Yellow -> System.out.println(PlayerName + " Won!");
                case Red -> System.out.println("AI Won!");
                default -> {
                    return;
                }
            }
            IsGameEnded = true;
        }
    }

    public boolean GameEnded()
    {
        return IsGameEnded;
    }
}
