package nye.teamC.Command;

import nye.teamC.Map;

public final class PrintMapCommand implements ICommand, IMapSetter
{
    private Map map;
    @Override
    public String name()
    {
        return "PrintMap";
    }

    @Override
    public String usage()
    {
        return "PrintMap";
    }

    @Override
    public boolean execute(final String args)
    {
        map.printMap();
        return true;
    }

    @Override
    public boolean hasArgs()
    {
        return false;
    }

    @Override
    public void setMap(final Map setmap)
    {
        map = setmap;
    }
}
