package nye.teamC.Command;

import nye.teamC.Map;

public class MoveCommand implements ICommand, IMapSetter
{
    Map map;

    @Override
    public String Name()
    {
        return "Move";
    }

    @Override
    public String Usage()
    {
        return "Move Height";
    }

    @Override
    public boolean Execute(String args)
    {
        try
        {
            int w = Integer.parseInt(args);
            return map.SetPlayer(w);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public void SetMap(Map setmap)
    {
        map = setmap;
    }
}
