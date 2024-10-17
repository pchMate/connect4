package nye.teamC.Command;

import nye.teamC.Managers.SaveManager;
import nye.teamC.Map;

public class SaveCommand implements ICommand, IMapSetter
{
    Map map;

    @Override
    public String Name()
    {
        return "Save";
    }

    @Override
    public String Usage()
    {
        return "Save filename";
    }

    @Override
    public boolean Execute(String args)
    {
        try
        {
            SaveManager.SaveMap(map, args);
            return true;
        }
        catch (Exception ex)
        {
            return  false;
        }
    }

    @Override
    public void SetMap(Map setmap)
    {
        map = setmap;
    }
}
