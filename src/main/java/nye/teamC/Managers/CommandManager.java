package nye.teamC.Managers;

import nye.teamC.Command.ICommand;
import nye.teamC.Command.CreateCommand;
import nye.teamC.Command.LoadCommand;
import nye.teamC.Command.SaveCommand;
import nye.teamC.Command.MoveCommand;
import nye.teamC.Command.QuitCommand;
import nye.teamC.Command.PrintMapCommand;
import nye.teamC.Command.PrintMapFancyCommand;

import java.util.HashSet;

public class CommandManager
{
    private final HashSet<ICommand> iCommands = new HashSet<>();

    public CommandManager()
    {
        iCommands.add(new CreateCommand());
        iCommands.add(new LoadCommand());
        iCommands.add(new SaveCommand());
        iCommands.add(new MoveCommand());
        iCommands.add(new QuitCommand());
        iCommands.add(new PrintMapCommand());
        iCommands.add(new PrintMapFancyCommand());
    }

    /** Getting the Command from Name
     * @param name CommandName
     * @return Null if not found or the Command itslef
     */
    public ICommand getCommand(final String name)
    {
        for (ICommand command : iCommands)
        {
            if (command.name().equals(name))
            {
                return command;
            }
        }
        return null;
    }

    /**
     * Printing Command Usage
     */
    public void printCommands()
    {
        for (ICommand command : iCommands)
        {
            System.out.println(command.usage());
        }
    }
}
