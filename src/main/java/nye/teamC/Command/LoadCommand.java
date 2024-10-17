package nye.teamC.Command;

import nye.teamC.Managers.SaveManager;
import nye.teamC.Map;

public class LoadCommand implements ICommand, IMapGetter
{
    Map map;

    @Override
    public String Name()
    {
        return "Load";
    }

    @Override
    public String Usage()
    {
        return "Load filename";
    }

    @Override
    public boolean Execute(String args)
    {
        map = SaveManager.LoadMap(args);
        return true;
    }

    public Map GetMap()
    {
        return map;
    }
}
