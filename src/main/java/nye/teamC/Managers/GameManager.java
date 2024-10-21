package nye.teamC.Managers;

import nye.teamC.Command.ICommand;
import nye.teamC.Command.IMapSetter;
import nye.teamC.Command.IMapGetter;
import nye.teamC.Command.MoveCommand;
import nye.teamC.Command.QuitCommand;
import nye.teamC.Map;

import java.util.Scanner;

public class GameManager
{
    private final CommandManager commandManager = new CommandManager();
    private final Scanner in = new Scanner(System.in);
    private String playerName;
    private Map map;
    private boolean isGameEnded = false;
    /**
     * Starting the Game
     */
    public void start()
    {
        System.out.println("Please Type your name to begin!");
        playerName = in.next();
        System.out.println("Commands available for your:");
        System.out.println("Commands Should be used as CommandName Parameter");
        commandManager.printCommands();
    }

    /** Use Command inside the Game.
     * @param command Command to use
     */
    public void useCommand(final String command)
    {
        if (isGameEnded)
        {
            System.out.println("Game has been ended!");
        }
        String args = "";
        ICommand iCommand = commandManager.getCommand(command);
        if (iCommand == null)
        {
            System.out.println("No command: " + command);
            return;
        }
        if (iCommand.hasArgs())
        {
            args = in.next();
        }
        if (iCommand instanceof QuitCommand)
        {
            isGameEnded = true;
            System.out.println("quitting!");
            return;
        }
        if (iCommand instanceof IMapSetter setter)
        {
            setter.setMap(map);
            System.out.println(iCommand.execute(args) ? "OK" : "Wrong Parameter!");
        }
        if (iCommand instanceof IMapGetter getter)
        {
            System.out.println(iCommand.execute(args) ? "OK" : "Wrong Parameter!");
            map = getter.getMap();
        }
        if (map != null)
        {
            checkWin();
            if (iCommand instanceof MoveCommand && !isGameEnded)
            {
                map.setAI();
                checkWin();
            }
        }
        else
        {
            System.out.println("Please Load or Create a new Map!");
        }
    }

    /**
     * Check who won, or currently no-one won
     */
    void checkWin()
    {
        var ret = map.check4();
        if (ret.getValue0())
        {
            switch (ret.getValue1())
            {
                case Yellow -> System.out.println(playerName + " Won!");
                case Red -> System.out.println("AI Won!");
                default -> {
                    return;
                }
            }
            isGameEnded = true;
        }
    }

    /** Get If the game has been ended or not.
     * @return Is the Game Ended
     */
    public boolean gameEnded()
    {
        return isGameEnded;
    }

    /** Get Internal Scanner (Input)
     * @return Internal In;
     */
    public Scanner getScanner()
    {
        return in;
    }
}
