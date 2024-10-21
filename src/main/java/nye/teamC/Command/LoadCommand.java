package nye.teamC.Command;

import nye.teamC.Managers.SaveManager;
import nye.teamC.Map;

public final class LoadCommand implements ICommand, IMapGetter
{
    private Map map;

    @Override
    public String name()
    {
        return "Load";
    }

    @Override
    public String usage()
    {
        return "Load filename";
    }

    @Override
    public boolean execute(final String args)
    {
        map = SaveManager.loadMap(args);
        return true;
    }

    @Override
    public boolean hasArgs()
    {
        return true;
    }

    public Map getMap()
    {
        return map;
    }
}
