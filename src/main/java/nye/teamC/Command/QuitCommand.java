package nye.teamC.Command;

public final class QuitCommand implements ICommand
{
    @Override
    public String name()
    {
        return "Quit";
    }

    @Override
    public String usage()
    {
        return "Quit";
    }

    @Override
    public boolean hasArgs()
    {
        return false;
    }

    @Override
    public boolean execute(final String args)
    {
        return false;
    }
}
