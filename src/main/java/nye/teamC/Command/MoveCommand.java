package nye.teamC.Command;

import nye.teamC.Map;

public final class MoveCommand implements ICommand, IMapSetter
{
    private Map map;

    @Override
    public String name()
    {
        return "Move";
    }

    @Override
    public String usage()
    {
        return "Move Height";
    }

    @Override
    public boolean execute(final String args)
    {
        try
        {
            int w = Integer.parseInt(args);
            return map.setPlayer(w);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean hasArgs()
    {
        return true;
    }

    @Override
    public void setMap(final Map setmap)
    {
        map = setmap;
    }
}
