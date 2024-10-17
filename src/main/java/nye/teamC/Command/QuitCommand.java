package nye.teamC.Command;

public class QuitCommand implements ICommand
{
    @Override
    public String Name()
    {
        return "Quit";
    }

    @Override
    public String Usage()
    {
        return "Quit";
    }

    @Override
    public boolean Execute(String args)
    {
        return false;
    }
}
