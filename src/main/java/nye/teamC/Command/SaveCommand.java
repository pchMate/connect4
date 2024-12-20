package nye.teamC.Command;

import nye.teamC.Managers.SaveManager;
import nye.teamC.Map;

public final class SaveCommand implements ICommand, IMapSetter
{
    private Map map;

    @Override
    public String name()
    {
        return "Save";
    }

    @Override
    public String usage()
    {
        return "Save filename";
    }

    @Override
    public boolean execute(final String args)
    {
        try
        {
            return SaveManager.saveMap(map, args);
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
