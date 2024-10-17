package nye.teamC.Managers;

import nye.teamC.Command.*;

import java.util.HashSet;

public class CommandManager
{
    HashSet<ICommand> Commands = new HashSet<>();

    public CommandManager()
    {
        Commands.add(new CreateCommand());
        Commands.add(new LoadCommand());
        Commands.add(new SaveCommand());
        Commands.add(new MoveCommand());
        Commands.add(new QuitCommand());
        Commands.add(new PrintMapCommand());
    }


    public boolean Execute(String name, String args)
    {
        for (ICommand command : Commands)
        {
            if (command.Name().equals(name))
            {
                return command.Execute(args);
            }
        }
        return false;
    }

    public ICommand GetCommand(String name)
    {
        for (ICommand command : Commands)
        {
            if (command.Name().equals(name))
            {
                return command;
            }
        }
        return null;
    }

    public void PrintCommands()
    {
        for (ICommand command : Commands)
        {
            System.out.println(command.Usage());
        }
    }
}
