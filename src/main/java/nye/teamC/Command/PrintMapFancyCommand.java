package nye.teamC.Command;

import nye.teamC.Map;

public final class PrintMapFancyCommand implements ICommand, IMapSetter
{
    private Map map;

    @Override
    public String name()
    {
        return "PrintMapFancy";
    }

    @Override
    public String usage()
    {
        return "PrintMapFancy";
    }

    @Override
    public boolean execute(final String args)
    {
        map.printMapFancy();
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
